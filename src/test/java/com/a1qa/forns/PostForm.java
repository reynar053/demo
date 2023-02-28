package com.a1qa.forns;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.concurrent.TimeoutException;

public class PostForm extends Form {
    private final static String DEFAULT_ID_SEPARATOR = "_";
    private final IButton showCommentsButton;
    private final IButton likeButton;
    private final ILabel postImage;
    private final ILabel postComment;
    private final ILabel postText;
    private final ILabel postAuthor;

    public PostForm(String userId, String postId) {
        super(By.xpath("//div[@data-post-id='" + userId + DEFAULT_ID_SEPARATOR + postId + "']"
                + "//div[contains(@class, 'wall_post_text')]"), "Post #" + postId + " written by user#" + userId);

        String postStartPath = "//div[@data-post-id='" + userId + DEFAULT_ID_SEPARATOR + postId + "']";
        showCommentsButton = AqualityServices.getElementFactory()
                .getButton(By.xpath(postStartPath + "//span[contains(@class, 'js-replies_next_label')]"), "The show comments button");
        likeButton = AqualityServices.getElementFactory()
                .getButton(By.xpath(postStartPath + "//div[contains(@class, 'PostButtonReactionsContainer')]"), "The Like button");
        postImage = AqualityServices.getElementFactory()
                .getLabel(By.xpath(postStartPath + "//a[contains(@href, '/photo')]"), "The post image");
        postComment = AqualityServices.getElementFactory()
                .getLabel(By.xpath(postStartPath + "//div[contains(@class, 'reply_author')]" +
                        "//a[contains(@class, 'author')]"), "Comment of the post");
        postText = AqualityServices.getElementFactory()
                .getLabel(By.xpath(postStartPath + "//div[contains(@class, 'wall_post_text')]"),
                        "Text of the post");
        postAuthor = AqualityServices.getElementFactory()
                .getLabel(By.xpath(postStartPath + "//*[contains(@class, 'post_author')]"), "Author of the post");
    }

    public String getCommentAuthorId() {
        return postComment.getAttribute("data-from-id");
    }

    public String getPostAuthor(){
        postAuthor.state().waitForExist();
        return postAuthor.getText();
    }

    public void clickShowComments() {
        showCommentsButton.click();
    }

    public void like() {
        likeButton.click();
    }

    public String getText() {
        return postText.getText();
    }

    public String getImageId() {
        return postImage.getAttribute("href").split(DEFAULT_ID_SEPARATOR)[1];
    }

    public String getChangedPostValue(String lastValue) {
        try {
            AqualityServices.getConditionalWait().waitForTrue(() -> !this.getText().equals(lastValue));
            return this.getText();
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPostDeleted() {
        return this.state().waitForNotDisplayed();
    }
}
