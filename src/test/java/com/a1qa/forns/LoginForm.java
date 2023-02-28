package com.a1qa.forns;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginForm extends Form {
    ITextBox emailField = AqualityServices.getElementFactory()
            .getTextBox(By.xpath("//input[@id='index_email']"), "Login email field");
    IButton submitEmail = AqualityServices.getElementFactory()
            .getButton(By.xpath("//button[contains(@class, 'VkIdForm__signInButton')]"),
                    "Login button", ElementState.EXISTS_IN_ANY_STATE);

    public LoginForm() {
        super(By.xpath("//input[@id='index_email']"), "Enter login form");
    }


    public LoginForm typeEmail(String email){
       emailField.type(email);
       return this;
    }

    public void clickSubmitEmail(){
        submitEmail.clickAndWait();
    }
}
