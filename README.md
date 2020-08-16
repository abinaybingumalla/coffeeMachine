# coffeeMachine
Dunzo Assignment

Requirements:
1) N beverages parallel
2) indicator that would show which all ingredients are running low
3) functional integration test cases for maximum coverage

Assumptions:
1) All beverage compositions are known

We will be loading all beverage compositions and available ingredients initially from txt files.
Low Indicator is configured as 10 in ResourceManager
Number of outlets are configured initially at the start of application
By Changing the outlets from 2 to 4 you can observe the change in preparation as black_tea is prepared according to "Output 2"

Algorithm:
All available ingredients are stored in concurrent map
Easy way is to synchronize the whole map, there is a major drawback
if tea requires ingredients 1, 2 and coffee require 3, 4 until tea is finished coffee will be blocked
To avoid this we will use Concurrent HashMap and also temporarily block(subtract) all required ingredients and store them in different map,
if all ingredients are available we can dispense else we put back blocked ingredients so that others can use either for new request or for retry mechanism.
