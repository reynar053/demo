package com.a1qa.forns;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class FeedForm extends Form {
    ILink myProfileLink = AqualityServices.getElementFactory().getLink(By.xpath("//li[@id='l_pr']//span[contains(@class, 'left_label inl_bl')]"), "Link 'My profile", ElementState.EXISTS_IN_ANY_STATE);

    public FeedForm() {
        super(By.xpath("//li[@id='l_pr']//span[contains(@class, 'left_label inl_bl')]"), "Feed form");
    }

    public void clickMyProfileLink() {
        AqualityServices.getBrowser().waitForPageToLoad();
        myProfileLink.clickAndWait();
    }
}
