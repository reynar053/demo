package com.a1qa;

import com.a1qa.api.VkApiUtils;
import com.a1qa.forns.FeedForm;
import com.a1qa.forns.LoginForm;
import com.a1qa.forns.PasswordForm;
import com.a1qa.forns.PostForm;
import com.a1qa.utils.Configs;
import com.a1qa.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VkTest extends BaseTest {
    @Test
    public void testVkPosting() {
        int randomStringLength = Integer.parseInt(Configs.getValueFromData("/randomStringsLength"));

        new LoginForm()
                .typeEmail(Configs.getValueFromData("/login"))
                .clickSubmitEmail();
        new PasswordForm().typePassword(Configs.getValueFromData("/password"))
                .clickSubmitPassword();
        new FeedForm().clickMyProfileLink();

        VkApiUtils vkApiUtils = new VkApiUtils();
        String testUserId = vkApiUtils.getCurrentUserId();
        String postText = StringUtils.randomString(randomStringLength);
        String postId = vkApiUtils.createNewPost(postText);

        PostForm vkPost = new PostForm(testUserId, postId);
        Assert.assertEquals(postText, vkPost.getText(), "The post with the sent text is not appeared on the wall");
        Assert.assertEquals(vkPost.getPostAuthor(), vkApiUtils.getUserName(), "Author of the post is not correct");

        String postTextNew = StringUtils.randomString(randomStringLength);

        String imageId = vkApiUtils.uploadImage(Configs.getValueFromData("/imageToBeUploaded"));
        vkApiUtils.editPost(postId, postTextNew, imageId);
        Assert.assertEquals(vkPost.getChangedPostValue(postText), postTextNew, "Post text was not updated");
        Assert.assertEquals(vkPost.getImageId(), imageId, "The uploaded picture is not the same as in post attachments");

        String commentMessage = StringUtils.randomString(randomStringLength);
        vkApiUtils.addCommentToPost(postId, commentMessage);
        vkPost.clickShowComments();
        Assert.assertEquals(vkPost.getCommentAuthorId(), testUserId, "Author of the comment is not correct");

        vkPost.like();
        Assert.assertEquals(vkApiUtils.isPostLikedByUser(postId), 1, "Post is not liked by the correct user");

        vkApiUtils.deletePost(postId);
        Assert.assertTrue(vkPost.isPostDeleted(),"The post is not deleted");
    }
}
