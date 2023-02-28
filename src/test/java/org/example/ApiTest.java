package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.api.APIConnector;
import org.example.configreader.ConfigManager;
import org.example.model.Post;
import org.example.model.users.User;
import org.example.common.JsonParser;
import org.example.utils.StringUtils;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class ApiTest extends BaseTest {
    @Test(testName = "Level 2. REST API (GET/POST) - JIRA #0001")
    public void getAllPostsTest() {
        SoftAssert softAssert = new SoftAssert();
        String url = BASE_URI + ConfigManager.getTestDataByPath("/postsTests/test1/domainPath").asText();
        int expectedStatusCode = ConfigManager.getTestDataByPath("/postsTests/test1/statusCode").asInt();
        String expectedContentType = ConfigManager.getTestDataByPath("/postsTests/test1/contentType").asText();

        response = APIConnector.makeGetRequest(url);
        softAssert.assertEquals(APIConnector.getStatusCode(response), expectedStatusCode, "Status code is not 200");
        softAssert.assertTrue(APIConnector.getContentType(response).contains(expectedContentType), "Response is not JSON");
        List<Post> responsePosts = JsonParser.stringJsonToObject(new TypeReference<>() {
        }, APIConnector.getResponseBodyAsString(response));
        List<Integer> postIds = responsePosts.stream().map(Post::getId).collect(Collectors.toList());
        List<Integer> sortedIds = postIds.stream().sorted().collect(Collectors.toList());
        softAssert.assertEquals(sortedIds, postIds, "Posts are not sorted ascending (by id)");

        url = BASE_URI + ConfigManager.getTestDataByPath("/postsTests/test2/domainPath").asText();
        expectedStatusCode = ConfigManager.getTestDataByPath("/postsTests/test2/statusCode").asInt();
        int expectedId = ConfigManager.getTestDataByPath("/postsTests/test2/responseBody/id").asInt();
        int expectedUserId = ConfigManager.getTestDataByPath("/postsTests/test2/responseBody/userId").asInt();

        response = APIConnector.makeGetRequest(url);
        softAssert.assertEquals(APIConnector.getStatusCode(response), expectedStatusCode, "Status code is not 200");
        Post responsePost = JsonParser.stringJsonToObject(new TypeReference<>() {}, APIConnector.getResponseBodyAsString(response));
        softAssert.assertEquals(responsePost.getId().intValue(), expectedId, "Id is not equal 99");
        softAssert.assertEquals(responsePost.getUserId().intValue(), expectedUserId, "User id is not equal 10");
        softAssert.assertFalse(responsePost.getBody().isEmpty(), "Body is empty");
        softAssert.assertFalse(responsePost.getTitle().isEmpty(), "Title is empty");

        url = BASE_URI + ConfigManager.getTestDataByPath("/postsTests/test3/domainPath").asText();
        expectedStatusCode = ConfigManager.getTestDataByPath("/postsTests/test3/statusCode").asInt();

        response = APIConnector.makeGetRequest(url);
        softAssert.assertEquals(APIConnector.getStatusCode(response), expectedStatusCode, "Status code is not 404");
        softAssert.assertTrue(JsonParser.stringToJson(APIConnector.getResponseBodyAsString(response)).isEmpty(), "Response is not empty");

        url = BASE_URI + ConfigManager.getTestDataByPath("/postsTests/test4/domainPath").asText();
        Post postForSending = new Post(ConfigManager.getTestDataByPath("/postsTests/test4/requestParams/userId").asInt(),
                StringUtils.generateRandomString(RANDOM_STRING_LENGTH),
                StringUtils.generateRandomString(RANDOM_STRING_LENGTH));
        expectedStatusCode = ConfigManager.getTestDataByPath("/postsTests/test4/statusCode").asInt();

        response = APIConnector.makePostRequest(url, postForSending.getPostAsMap());
        softAssert.assertEquals(APIConnector.getStatusCode(response), expectedStatusCode, "Status code is not 200");
        softAssert.assertEquals(postForSending.getTitle(), JsonParser.stringJsonToObject(new TypeReference<Post>() {
                }, APIConnector.getResponseBodyAsString(response)).getTitle(), "Titles are not the same");

        url = BASE_URI + ConfigManager.getTestDataByPath("/usersTests/test5/domainPath").asText();
        User expectedUser = JsonParser.jsonToObject(ConfigManager.getTestDataByPath("/usersTests/test5/responseBody"), User.class);
        expectedStatusCode = ConfigManager.getTestDataByPath("/usersTests/test5/statusCode").asInt();
        expectedContentType = ConfigManager.getTestDataByPath("/usersTests/test5/contentType").asText();

        response = APIConnector.makeGetRequest(url);
        softAssert.assertEquals(APIConnector.getStatusCode(response), expectedStatusCode, "Status code is not 200");
        softAssert.assertTrue(APIConnector.getContentType(response).contains(expectedContentType), "Response is not JSON");
        List<User> responseUsers = JsonParser.stringJsonToObject(new TypeReference<>() {}, APIConnector.getResponseBodyAsString(response));
        User userWithTheSameIdAsExpected = responseUsers.stream()
                .filter(user -> user.getId() == expectedUser.getId()).findAny().orElse(null);
        softAssert.assertEquals(userWithTheSameIdAsExpected, expectedUser, "User (id=5) data is not equals to expected User data");

        url = BASE_URI + ConfigManager.getTestDataByPath("/usersTests/test6/domainPath").asText();
        User expectedResponse = JsonParser.jsonToObject(ConfigManager.getTestDataByPath("/usersTests/test5/responseBody"), User.class);
        expectedStatusCode = ConfigManager.getTestDataByPath("/usersTests/test6/statusCode").asInt();

        response = APIConnector.makeGetRequest(url);
        softAssert.assertEquals(APIConnector.getStatusCode(response), expectedStatusCode, "Status code is not 200");
        User actualResponse = JsonParser.stringJsonToObject(new TypeReference<>() {}, APIConnector.getResponseBodyAsString(response));
        softAssert.assertEquals(actualResponse, expectedResponse, "User data does not match with expected User data");

        softAssert.assertAll();
    }
}
