# seca-project1
##Java Beer Tapper


  #### Technologies used
  
  - Java 
  - Processing 
  - Bartender control
  - Multiple Classes 
    - Bar, Bartender, Beer, Customer,  GameOver, GetReady, Level, Lives, MainClass, Points
  - Collision detection
  - Using two draw methods
    - makeBeersMove
    - makeCustomersMove


  
  #### Process/approach
  Using the planning and logic of a game previously created using Javascript single file code, implement classes in Java for logic and Processing for graphics
  
  #####Rules
  ######Bartender moves up and down between the four rows of bars.
  - Can also loop around from the bottom to the top and from the top to the bottom
  - Can move to the left to catch a beer
  - Can jump back to tap by pouring key
  - Can stop pouring by moving to another row
  ######Ways to lose a guy:
  - Customer makes it to the end of the row without getting a beer
  - Bartender sent too many beers
  - Bartender does not collect the empty glass
  - Music is on and customers start dancing and beer was served *optional
   ######Levels 1 & 2 States:
  - Customer comes to row - level 2 sends two customers
  - Bartender pours beer
  - Bartender sends beer
  - Customer gets beer
  - Customer drinks beer
  - Customer sends empty glass
  - Bartender must catch glass
  - Sometimes there is money on bar for tip *optional
   ######Points:
  - 50 Points for each saloon patron you send off his aisle
  - 100 Points for each empty mug you pick up
  - 1500 Points for each tip you pick up *optional
  - 1000 Points for completing a level
  - Bonus Level 3000 Points for getting the bonus level right *optional


  
  #### Installation instructions
    
  Open project in ItelliJ and run
  
  #### Unsolved problem/ Future Features
Two players, High scores, Graphics, Sound, Bonus round

  
  #### Challenges
   ######Classes
   Breaking down the Main Class even further. 
   ######Collisions
   Thinking about how to break out of the draw method and reset level
   
   Lots of conditional statements that make it hard to take it all in at once
   ######Private, Public and Static
   Not completely clear on when to use which and why
   
   #### Biggest wins
   ######Planning 
   Most of it done already, could focus on building
   ######Refactoring
   Javascript into Java and Processing
   ######Debugging
   IntelliJ hints and errors messages, could pinpoint the location of the syntax error or where to look in the logic
   ######Using classes
   Finally able to use classes in a project


  
