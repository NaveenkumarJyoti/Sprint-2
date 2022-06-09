Feature: Negative fucntionality testing of JPetSotrer.

@Signup
Scenario: Cannot register with invalid details.
	Given User is on sign up page
	When Enter invalid details
	And Click on save account information
	Then Sign up failed and error message is displayed

@Login
Scenario: Unable to login through invalid credentials.
	Given User is on login page
	When Enter invalid credentials
	|Jonah | jona@123 |
	And Click on login
	Then Login failed message is displayed
	
@Search
Scenario Outline: Cannot find the pet with wrong id or wrong search item.
	Given User clicks on search
	When User enters <product>
	And Click on search
	Then No relevant products are found
	Examples:
	| product |
	|football |