@securityFlow
Feature: Testing API for authenticate users
  More info in https://bookstore.toolsqa.com/swagger/index.html

  @success
  Scenario: User authenticate successfully
    Given An authentication Services
    When I send username "luisCordova" and password "P@$$w0rd"
    Then The services response success
    And Generate a new Token

  @errors
  Scenario: User authenticate with error
    Given An authentication Services
    When I send username "luisCordova" and password "IncorrectPassword"
    Then The services response with error
    And Dont generate any token