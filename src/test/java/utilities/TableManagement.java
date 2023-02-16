package utilities;

import common.logger.LoggerSingleton;
import models.User;
import pageobjects.forms.RegistrationForm;

public abstract class TableManagement {
    public static void fillRegistrationFormWith(RegistrationForm registrationForm, User user){
        LoggerSingleton.info("Registration form is filling with {}", user.toString());
        registrationForm.typeFirstName(user.getMap().get("First Name"));
        registrationForm.typeLastName(user.getMap().get("Last Name"));
        registrationForm.typeEmail(user.getMap().get("Email"));
        registrationForm.typeAge(user.getMap().get("Age"));
        registrationForm.typeSalary(user.getMap().get("Salary"));
        registrationForm.typeDepartment(user.getMap().get("Department"));
    }
}
