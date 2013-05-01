Feature: User Management
    
    Scenario: Create a new basar user
        Given a basar with no users
        When create a user with number "100" and name "Christian"
        Then the count of the basar users should be 1