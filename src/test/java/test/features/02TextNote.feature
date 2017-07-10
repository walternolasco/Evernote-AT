Feature: TextNote

Scenario Outline: <arg1> textnote
Given A user that wants to <arg2> a textnote
When I <arg2> a textnote
Then it will <arg3> the textnote


Examples:
|   arg1         |   arg2     |   arg3    |   arg4    |
|   create       |   create   |   create  |   create  |
|   edit         |   edit     |   edit    |   edit    |
|   delete       |   delete   |   delete  |   delete  |