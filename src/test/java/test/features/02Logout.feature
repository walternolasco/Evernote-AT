@Login
Feature: Logout

Scenario: <arg1> logout
Given a user already logged in
When I go to settings
And go to account information
And tap the logout button
Then I can logout of the app 
