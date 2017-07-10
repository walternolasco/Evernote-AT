Feature: Logout

Scenario: logout
Given a user already logged in
When I go to settings
And go to account information
And tap logout
Then I can logout successfully
