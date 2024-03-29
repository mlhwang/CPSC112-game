Code Crush
=============

This is intended as an instruction module to teach intro students about
loops, arrays, objects, and the like.
This is based off David Saltares's [code](https://github.com/siondream/freegemas-gdx),
which is based off José Tomás Tocino García's [code](http://code.google.com/p/freegemas/),
which is based off the classic game of Bejeweled.

###Setup/Installation###

follow the instructions on how to [set up](https://developer.android.com/sdk/index.html?hl=i)
the development environment. Then download the github project as a zip file and import in Eclipse.


###Assignments###

Just delete sections of the code and ask students to fill in the blanks.
Most likely, you will be interested in asking students to rewrite part of
Board.java or possibly other files in the freegemas folder (the logic)


#Example Assignment

Total points: 45

You may be familiar with Candy Crush or Bewjewled. The goal is to swap items on a board to create matches of three or more. When such a match is created, those items disappear and you are awarded points. In our game the only goal is to score points. We are going to be building this game ourselves!

The only file you will need to edit is Board.java located in the "freegemas" package. The following instructions will be in reference to this file. The file is mostly complete, you will just be asked to fill in some missing parts. Reading, understanding, and editing someone else's code is one of most the painful and educational things you can do as a programmer.

For this assignment we have given you a .zip file called CPSC112_Assignment4.zip which can be imported into your workspace as per the instructions in Resources/app_setup.html.

CPSC112_Assignment4.zip is a complete project that can be run as an Android, Desktop, or Web app. To run the desktop version, you simply click on the freegemas-desktop package in the sidebar in eclipse and press run. Likewise with Android. Feel free to test your code using either method.

The whole project has a bit of a complicated structure - there are four different folders. The one titled "freegemas" contains the file you will be editing and is where all the logic is handled, while the other three "frontend-_____" are for the front end and you won't need to touch. Specifically, you need to edit freegemas/src/com.sionfream.freegemas/Board.java

#Part 1
20 points

Methods to edit

- buildPossibleMatchHorizontal
- buildPossibleMatchVertical
- expandArray

Before you get started coding, let's take a look at the program as it is right now. Select frontend-desktop folder, then click the run button in eclipse. Up comes an initial board, but there are two problems.

The first is that initial board could have matches already in it. In our fillInitialBoard method, we are using a "brute force" approach to this problem. We just keep generating boards until we find one that doesn't have any matches in it initially.  A do-while executes once, then checks the condition to see if it needs to run again (see Section 5.1 from the textbook for more). fillInitialBoard is completed for you, but you need to finish two of the submethods, "buildPossibleMatchHorizontal" and "buildPossibleMatchVertical".

These methods are called from "find_matches", which is called from "has_matches", which is called by fillInitialBoard. When you are ready to get started on these two methods, enable the has_matches method by commenting out the return line.

buildPossibleMatchHorizontal/Vertical is going to take in an 'x' and a 'y' that specify a position on the board, and return the list of matches including that square. For example, if we had the following board calling findMatchVertical(0,0) should return an array [(0,0),(0,1)] and calling (0,0) should return an array [(0,0)].

![Alt Board](/Board1.png)

###How to write findMatchHorizontal/Vertical
There are a few ways to write these methods, but we would like you to do the following.

Start with an array of length 1, loop down the row/column while there are matches. Each time you find a match, expand the array by one, then fill in that space with a "new Point(x,y)". You will need to fill in the expandArray method that takes an old_array and return a new_array such that "new_array.length==old_array.length+1" and all the elements are copied over from the old_array.

#Part 2
15 points

Methods to edit
- findSolutions

Great, now you can play the game! There is only one (major) problem left. If you run out of moves you are just stuck. In fact the initial board could even be generated in such a way that there are no solutions right from the beginning. We certainly don't want that.

Have you noticed that "Hint" button in the game? It would be nice if it actually worked too. When we click that button, all the squares that can be swapped to create a match should be highlighted. We have taken care of the highlighting, you just need to implement the "findSolutions" method so that it returns a list of all the squares that could be swapped.

We will write this function that finds solutions by swapping every square in every direction and checking to see if it makes a match (this is again a "brute force" approach). Notice that we don't know how big how list of solutions is going to be when we start. That means we can reuse our expandArray function from the last part.
