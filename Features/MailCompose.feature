#Author:kumardhananjay@outlook.in
Feature: Testing of Compose Function

@Compose
Scenario Outline: Verify Compose Functionality in Gmail
	Given User Launched Browser and Navigated to URL
	Then User Login with UserName and Password
	Then User Compose Email and Attach Excel file
	
	Examples:
	|	URL	| BrowserName	|	BrowserVersion	|
	|	UAT	|	Chrome		|	Version 89		|