@tag
Feature: Purchase a product from ECommerce website
  Purchasing a product from ECommerce website

  Background: 
    Given user navigates to application

  @Regression
  Scenario Outline: Purchase a product
    Given user logs in to application "<email>" and "<password>"
    When user adds the product "<product>" to cart
    Then user checksout product name "<product>" and selects "<country>" and submit Order
    And user verifies "THANKYOU FOR THE ORDER." message in the Confirmation page

    Examples: 
      | email                  | password   | product     | country |
      | thomasshelby@gmail.com | Thomas@123 | ZARA COAT 3 | India   |
      #| adashelby@gmail.com    | Adashelby@123 | ADIDAS ORIGINAL | India   |
