package pageobjects.forms;

import org.openqa.selenium.By;
import pageobjects.elements.Button;
import pageobjects.elements.TextBox;
import basepage.BaseForm;

public class RegistrationForm extends BaseForm {
    private final TextBox firstName = new TextBox(By.xpath("//form[@id='userForm']//input[@id='firstName']"), "First Name");
    private final TextBox lastName = new TextBox(By.xpath("//form[@id='userForm']//input[@id='lastName']"), "Last Name");
    private final TextBox email = new TextBox(By.xpath("//form[@id='userForm']//input[@id='userEmail']"), "Email");
    private final TextBox age = new TextBox(By.xpath("//form[@id='userForm']//input[@id='age']"), "Age");
    private final TextBox salary = new TextBox(By.xpath("//form[@id='userForm']//input[@id='salary']"), "Salary");
    private final TextBox department = new TextBox(By.xpath("//form[@id='userForm']//input[@id='department']"), "Department");
    private final Button submitButton = new Button(By.xpath("//button[@id='submit']"), "Submit");

    public RegistrationForm() {
        super(By.xpath("//form[@id='userForm']"), "Registration form");
    }

    public void typeFirstName(String firstName) {
        this.firstName.type(firstName);
    }

    public void typeLastName(String lastName) {
        this.lastName.type(lastName);
    }

    public void typeEmail(String email) {
        this.email.type(email);
    }

    public void typeAge(String age) {
        this.age.type(age);
    }

    public void typeSalary(String salary) {
        this.salary.type(salary);
    }

    public void typeDepartment(String department){
        this.department.type(department);
    }

    public void submitForm() {
        submitButton.click();
    }
}
