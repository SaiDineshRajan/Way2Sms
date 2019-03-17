Feature:Logout
@smoketest
Scenario:validate logout operation
Given launch site using "chrome"
When do login with valid data
|  mbno     | pwd      |
|9912847475 |9912847475|
And do logout
Then homepage will be reopened
And close site