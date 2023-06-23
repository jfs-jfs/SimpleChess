# TQS_practica : A game of "chess"

## How to play:

### Running the game

To run the game from eclipse just press the Run button. A menu will apear and then just enter the number one as the option to play is the number one.

### Making moves

To make moves a bit of chess notation is necessary. Each turn you will be asked to enter the next move in a simpler form of chess notation or just an "X" to falg that you surrender.
The format of the chess notation I chose is:

> [Piece identifier](column of the piece)[destination square]

The column of the piece is an optional argument when there is only a piece of that type that can do that move. Use it better when to pieces of the same type can go to the same place.

###### The identifiers of pieces are as follows:
+ The pawn = "" nothing
+ The rook = "R"
+ The bishop = "B"
+ The knight = "N"
+ The queen = "Q"
+ The king = "K"


###### The valid squares:
The valid squares start at "a1" all the way to "h1" going up till "h8"

###### Some examples:
+ Move a knight to the square c3 = "Nc3"
+ Move a pawn to e4 = "e4"

### Things to take into account
+ The movement castle isn't in this version.
+ There is no option to choose the piece the pawn promotes to. It will promote to a queen in this version.
+ You need to kill the king to win.
