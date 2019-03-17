Feature:Login

Background:
Given launch site
When browser is chrome
And follow the remaining scenarios 
Scenario:validate Site launch
Given launch site using "chrome"
Then title contains "Free SMS"
And close site

Scenario Outline:validate login operation
Given launch site using "chrome"
When enter mobile number as "<x>"
And enter password as "<y>"
And click login
Then validate output for criteria as "<z>"
And close site
Examples:
|      x      |        y         |     z       |
| 9912847475  |    9912847475    |all_valid    |
|             |    9912847475    |mbno_blank   |
| 9640946262  |    9912847475    |mbno_invalid |
| 9912847475  |                  |pwd_blank    |
| 9912847475  |    12345678      |pwd_invalid  |

