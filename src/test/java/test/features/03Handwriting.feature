Feature: Handrwriting

Scenario Outline: <arg1> handwriting
Given A user that wants to <arg2> a handwriting
When I <arg2> a handwriting
Then it will <arg3> the handwriting


Examples:
|   arg1         |   arg2     |   arg3    |   arg4    |
|   create       |   create   |   create  |   create  |
|   edit         |   edit     |   edit    |   edit    |
|   delete       |   delete   |   delete  |   delete  |