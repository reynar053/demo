package com.a1qa.forns;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PasswordForm extends Form {
    ITextBox passwordField = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//input[@type='password']"), "Login password field");
    IButton submitPassword = AqualityServices.getElementFactory()
            .getButton(By.xpath("//div[contains(@class, 'vkc__EnterPasswordNoUserInfo__buttonWrap')]//button[@type='submit']"),
                    "Continue button", ElementState.EXISTS_IN_ANY_STATE);

    public PasswordForm() {
        super(By.xpath("//input[@type='password']"), "Entering password form");
    }

    public PasswordForm typePassword(String password){
        passwordField.type(password);
        return this;
    }

    public void clickSubmitPassword(){
        submitPassword.clickAndWait();
    }
}
