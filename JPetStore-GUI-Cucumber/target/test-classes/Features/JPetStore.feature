Feature: Complete business flow of JPetStorer.

@SignUp
Scenario: Create an account with JPetStorer.
	Given User navigates to sign up page
	When User enters necessary details
	Then Account is created
	And User enters invalid data in mandatory fields
	Then Error message
	And User leaves mandatory fields blank
	Then Error message is displayed
	
@Login
Scenario Outline: Login through valid credentials.
	Given User is on login page
	When User enters valid credentials
	| Velverde | Vel@123 |
	And User clicks on login
	Then Logged in successfully
	And User checks logo 
	Then User finds logo on top left corner
	And User looks for welcome message
	Then User reads the welcome message
	
@SearchForPet
Scenario: Search for pet.
	Given User enters petname in search
	When User clicks on search
	Then Respective pets are displayed
	And User clicks on petId
	Then Pet varieties are displayed
	And User clicks on certain pet in categories
	Then User is navigated to the respective pet page
	And User clicks on product id
	Then Navigates to the varieties available
	And User clicks on item id
	Then Respective pet details are displayed
	
@AddToCart	
Scenario: Add to cart
	Given User is on pet page
	When User clicks on add to cart
	Then Navigated to cart
	And User changes the quantity 
	When User clicks on update cart
	Then Quantity and amount gets updated
	And User clicks on remove 
	When Pet is removed from cart
	Then Cart empty message is displayed
	And User clicks on return to main menu
	Then Navigated to the main menu
 	
@Checkout
Scenario:Proceed to checkout and place the order.
	Given User navigates to cart and click on checkout
	When Place the order
	And Order is placed
	Then User should see the successfully placed order message
	
@DeleteOrder
Scenario: User should be able to delete order by selecting orderId.
	Given User navigates to my orders
	When User selects a particular order id
	And Clicks on delete order
	Then Order deleted successfully