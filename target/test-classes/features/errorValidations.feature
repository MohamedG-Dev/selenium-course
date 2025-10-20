Feature: Error Validations

  Background: 
    Given user navigates to application

  @ErrorValidation @Regression
  Scenario Outline: Error validation
    Given user logs in to application "<email>" and "<password>"
    Then check for error message "Incorrect email or password."

    Examples: 
      | email            | password   |
      | shelby@gmail.com | Thomas@123 |
