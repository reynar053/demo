package com.a1qa.api;

import aquality.selenium.browser.AqualityServices;
import com.a1qa.api.beans.ApiEndpoint;
import com.a1qa.api.common.JsonParser;
import com.a1qa.api.connection.ConnectionFactory;
import com.a1qa.utils.Configs;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

public class VkApiUtils {
    private final String apiUri = Configs.getValueFromData("/apiUri");

    public String createNewPost(String text) {
        AqualityServices.getLogger().info("Creating new post with text: %s", text);
        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/newPost"));

        Map<String, String> params = buildDefaultParameters();
        params.put(apiEndpoint.getParameters().get("postText"), text);

        return sendDefaultRequest(apiEndpoint, params).at("/response/post_id").asText();
    }

    public String getUserName(){
        AqualityServices.getLogger().info("Getting username");
        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/getUserInfo"));
        JsonNode userInfo = sendDefaultRequest(apiEndpoint);
        String firstname = userInfo.at("/response/first_name").asText();
        String surname = userInfo.at("/response/last_name").asText();
        return firstname + " " + surname;
    }

    /**
     * @param postId - id of the post that will be changed
     * @param newText - new text of the post
     * @param imageId - path to the image that will be attached to the post
     */
    public void editPost(String postId, String newText, String imageId) {
        AqualityServices.getLogger().info("Editing post %s. New text: '%s', new Image '%s'", postId, newText, imageId);
        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/editPost"));

        Map<String, String> params = buildDefaultParameters();
        params.put(apiEndpoint.getParameters().get("postId"), postId);
        params.put(apiEndpoint.getParameters().get("postText"), newText);
        params.put(apiEndpoint.getParameters().get("attachments"), "photo" + getCurrentUserId() + "_" + imageId);

        sendDefaultRequest(apiEndpoint, params);
    }

    /**
     * @param pathToImage - path to the picture that will be loaded
     * @return - image id on the vk server.
     */
    public String uploadImage(String pathToImage) {
        String targetAlbum = getVkAlbumId();
        JsonNode uploadServerResponse = uploadPhotoOnVkServer(pathToImage, targetAlbum);
        return savePhotoOnVkServer(targetAlbum, uploadServerResponse).at("/response/0/id").asText();
    }

    private JsonNode uploadPhotoOnVkServer(String pathToImage, String targetAlbum){
        AqualityServices.getLogger().info("Uploading %s on VK server", pathToImage);
        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/uploadPhotoOnVkServer"));

        Map<String, String> params = buildDefaultParameters();
        params.put(apiEndpoint.getParameters().get("targetAlbumId"), targetAlbum);
        return ConnectionFactory.post().buildMultipartRequestParameters(pathToImage).send(getVkUploadServer()).getResponseBodyAsJson();
    }

    private JsonNode savePhotoOnVkServer(String targetAlbum, JsonNode response) {
        AqualityServices.getLogger().info("Saving photo on VK server in %s album", targetAlbum);
        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/savePhotos"));
        Map<String, String> params = buildDefaultParameters();
        params.put(apiEndpoint.getParameters().get("targetAlbumId"), targetAlbum);
        params.putAll(JsonParser.stringJsonToObject(new TypeReference<Map<String,String>>(){}, response.toString()));
        return sendDefaultRequest(apiEndpoint, params);
    }

    private String getVkUploadServer() {
        AqualityServices.getLogger().info("Getting upload server");
        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/getUploadServer"));
        Map<String, String> params = buildDefaultParameters();
        params.put(apiEndpoint.getParameters().get("targetAlbumId"), getVkAlbumId());
        return ConnectionFactory.get()
                .buildRequestParameters(params)
                .send(apiUri + apiEndpoint.getUrl())
                .getResponseBodyAsJson()
                .at("/response/upload_url").asText();
    }

    private String getVkAlbumId() {
        AqualityServices.getLogger().info("Getting target album ID");

        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/getAlbums"));
        return sendDefaultRequest(apiEndpoint).at("/response/items/0/id").asText();
    }

    public void addCommentToPost(String postId, String commentText) {
        AqualityServices.getLogger().info("Adding comment with text '%s' to post with id '%s'", commentText, postId);

        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/addComment"));
        Map<String, String> params = buildDefaultParameters();

        params.put(apiEndpoint.getParameters().get("postId"), postId);
        params.put(apiEndpoint.getParameters().get("ownerId"), getCurrentUserId());
        params.put(apiEndpoint.getParameters().get("commentText"), commentText);
        sendDefaultRequest(apiEndpoint, params);
    }

    public int isPostLikedByUser(String postId) {
        JsonNode post = getPostById(postId);
        return post.at("/response/0/likes/user_likes").asInt();
    }

    public void deletePost(String postId) {
        AqualityServices.getLogger().info("Deleting post #%s", postId);
        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/postDeletion"));

        Map<String, String> params = buildDefaultParameters();
        params.put(apiEndpoint.getParameters().get("postId"), postId);
        params.put(apiEndpoint.getParameters().get("ownerId"), getCurrentUserId());
        sendDefaultRequest(apiEndpoint, params);
    }

    private JsonNode getPostById(String postId) {
        AqualityServices.getLogger().info("Getting post with id %s", postId);
        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/getPostById"));
        Map<String, String> params = buildDefaultParameters();
        params.put(apiEndpoint.getParameters().get("postList"), getCurrentUserId() + "_" + postId);
        return sendDefaultRequest(apiEndpoint, params);
    }

    public String getCurrentUserId() {
        ApiEndpoint apiEndpoint = JsonParser.stringJsonToObject(new TypeReference<ApiEndpoint>(){}, Configs.getValueFromData("/apiEndpoints/getUser"));
        return sendDefaultRequest(apiEndpoint).at("/response/0/id").asText();
    }

    private JsonNode sendDefaultRequest(ApiEndpoint apiEndpoint){
        return sendDefaultRequest(apiEndpoint, buildDefaultParameters());
    }

    private JsonNode sendDefaultRequest(ApiEndpoint apiEndpoint, Map<String, String> params){
        return ConnectionFactory.get().buildRequestParameters(params).send(apiUri + apiEndpoint.getUrl()).getResponseBodyAsJson();
    }

    private Map<String, String> buildDefaultParameters() {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", Configs.getValueFromData("/token"));
        params.put("v", Configs.getValueFromData("/apiOauth/version"));
        return params;
    }
}
