04/27/2023 online - 30 mins
debated between 2048 and blackback and decided on 2048 as our game

4/28-5/09 absent from ap testing

5/10: eclipse had an issue and worked with team to debug the issue on discord online (2 hours)

5/11: started working on backend, created two classes: board and tile (tile keeps track of each "box" and then
board keeps track of a 4x4 grid of tiles). created methods for each class and created the initialization method
for the board to randomly set two tiles (whole time in class)

5/12: debugged some errors for initializing two random tiles where it wasn't 0 indexed and commented out code for colors of tiles that created errors in the tile class (20 min at home)

5/13: relearned switch syntax and use case and created framework for the 4 different moves to be individually and differently processed (20 min at home)

5/15: worked on the ball.java file for the breakout game. created the dx and dy speeds, checked act method both to move the ball as when as bounce off the walls when (45 min in class).

5/16: worked on the moveup/down/right/left functions for the board class and subsequently called whichever one is necessary when the user inputs something (15 min in class).

5/18: worked on the logic for the up move. checked the first condition if the top row/column was first empty in the beginning such that the logic is easier. potential idea to recursively call the moveup function to simplify the game logic. (90 min at class)

5/20: continued to update game logic for the moveup move. planning on making sure the up move works so that creating the code for the other 3 moves should be relatively simple because the logic should be the same. for the move up function, checked conditions to move a tile to the top if the top is empty as well as combine w/ another value if it is empty (30 min at home)

5/22: got the moveup and movedown to work. called moveup/downcolumn 4 times to make the logic easier and make the code cleaner. mike is working on randomly generating the new tiles in empty squares after the move is done and we used eashan's console version of the game to test the moves out. 45 minutes in class

5/23: got the moveright and moveleft functions to work. same logic as moveup/down but just switched to going through rows rather than moving through columns. also created the isSame function to check if the board is the same before and after a move, b/c if so then the board shouldn't have a random tile added (60 mins at home)

5/24: created the hasNextMove function which checks if the game is over. created a copy of the game, did the 4 moves, and if the board is still the same after the four moves, that means the game is over because all of the 4 moves didn't have an impact (30 mins at home)

5/26: created the gameWon() funciotn that checks if the board has a 2048 tile and then returns true if it does. debugging the hasnextmove function, there might potentially be an error with doing all 4 moves and then checking if the board is still the same but not quite sure (30 mins at home)

5/30: got the hasgamewon and hasnextmove function to work. needed to check if there was any blank tiles b/c my code makes the four moves and if the board is the same then it says game over. not sure why it didn't work, but it works now that i checked the blank tiles (45 mins in class)

