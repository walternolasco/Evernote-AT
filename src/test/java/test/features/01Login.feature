Feature: Login

Scenario Outline: <arg1> login
Given A <arg2> username
And A <arg3> password
When I submit the credentials
Then I <arg4> login


Examples:
|   arg1         |   arg2      |   arg3    |   arg4    |
|   Unsuccessful |   invalid   |   invalid |   cannot  |
|   Successful   |   valid     |   valid   |   can     |