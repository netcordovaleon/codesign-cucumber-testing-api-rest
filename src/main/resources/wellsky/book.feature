@bookFlow
Feature: End to end test for Book store API
  More info in https://bookstore.toolsqa.com/swagger/index.html

  Background: User generate token for authentication
    Given I am an authorized user

  Scenario: Authorized user is able to add a new book
    Given A list of book are available
    When I add a book to my reading list
    Then The book is added
    Then The book has a correct JSON structure
