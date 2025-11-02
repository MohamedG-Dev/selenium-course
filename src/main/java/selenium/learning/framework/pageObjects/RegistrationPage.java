package selenium.learning.framework.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.learning.framework.utilities.Utility;

public class RegistrationPage extends Utility {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "userMobile")
    private WebElement mobileInput;

    @FindBy(css = "select[formcontrolname='occupation']")
    private WebElement occupationSelect;

    private By genderOptions = By.cssSelector("input[formcontrolname='gender']");

    @FindBy(id = "userPassword")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(css = "input[formcontrolname='required']")
    private WebElement termsCheckbox;

    // submit input has id "login" and value="Register"
    @FindBy(id = "login")
    private WebElement registerButton;

    // optional: generic area for inline validation / messages (if present)
    @FindBy(css = ".login-wrapper-footer-text, .text-danger, .alert")
    private List<WebElement> messages;

    public void enterFirstName(String firstName) {
        sendKeysAction(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeysAction(lastNameInput, lastName);
    }

    public void enterEmail(String email) {
        sendKeysAction(emailInput, email);
    }

    public void enterMobile(String mobile) {
        sendKeysAction(mobileInput, mobile);
    }

    public void enterPassword(String password) {
        sendKeysAction(passwordInput, password);
    }

    public void enterConfirmPassword(String password) {
        sendKeysAction(confirmPasswordInput, password);
    }

    public void selectOccupation(String occupationVisibleText) {
        scrollIntoViewAction(occupationSelect);
        occupationSelect.click();
        occupationSelect.findElements(By.tagName("option")).stream()
                .filter(opt -> opt.getText().trim().equals(occupationVisibleText))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void selectGender(String genderValue) {
        // genderValue should be "Male" or "Female" as in DOM
        driver.findElements(genderOptions).stream()
                .filter(e -> genderValue.equalsIgnoreCase(e.getAttribute("value")))
                .findFirst()
                .ifPresent(e -> {
                    scrollIntoViewAction(e);
                    e.click();
                });
    }

    public void setAcceptTerms(boolean accept) {
        scrollIntoViewAction(termsCheckbox);
        boolean isSelected = termsCheckbox.isSelected();
        if (accept != isSelected) {
            termsCheckbox.click();
        }
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String mobile,
                                     String occupation, String gender, String password, boolean acceptTerms) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterMobile(mobile);
        selectOccupation(occupation);
        selectGender(gender);
        enterPassword(password);
        enterConfirmPassword(password);
        setAcceptTerms(acceptTerms);
    }

    public LoginPage submitRegistration() {
        scrollToElementAndClickAction(registerButton);
        return new LoginPage(driver);
    }

    public LoginPage fillAndSubmit(String firstName, String lastName, String email, String mobile,
                                   String occupation, String gender, String password, boolean acceptTerms) {
        fillRegistrationForm(firstName, lastName, email, mobile, occupation, gender, password, acceptTerms);
        return submitRegistration();
    }

    public String getAnyMessageText() {
        try {
            if (!messages.isEmpty()) {
                return messages.get(0).getText().trim();
            }
        } catch (Exception e) {
            // ignore
        }
        return "";
    }
}