import java.util.*;  //used for input

/******************************************************************************
* QueenSimulation is a brute-force solver for the N-Queens Problem,
* in which one is challenged to place n queens on a chessboard of size (n by n)
* squares so that none of the queens can attack or be attacked by any others.
*
* This class was written for CSC 103 Data Structures, Project 3,
* in which a brute-force solution to the N-Queens Problem was implemented
* using a LinkedStack ADT.
*
* @author Daniel Breslawski, Timothy Keene
*
******************************************************************************/
class QueenSimulation {
   public static void main(String[] args) {
      //Setup stack and test queen
      LinkedStack<Queen> board = new LinkedStack<Queen>();
      Queen testQueen = new Queen();
      //Setup variables and scanner
      Scanner scan = new Scanner(System.in);
      boolean canPlace = true;
      boolean solutionFound = false;
      int solutionCounter = 1;
      int numOfRows = 0;
      //We setup iNum and jNum to determine the starting spot for the rows and columns.
      //(Will always be 0 on the first run)
      int iNum = 0;
      int jNum = 0;
      
      //Welcome message and grab user input
      boolean validInput = false;
      while (!validInput) {
         try {
            System.out.println("Welcome to the N-Queens Solver!");
            System.out.println("Please enter the board size (must be at least 4): ");
            numOfRows = scan.nextInt();
            //Make sure it's a solvable board size
            if (numOfRows < 4)
               throw new Exception("Please enter a valid board size (must be at least 4).");
            validInput = true;
         }
         catch (InputMismatchException e) {
            System.out.println("Please enter a number in integer form.");
            //Clear buffer
            scan.next();
         }
         catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
      //Run loop to find solutions
      while (!solutionFound) {
         //Outer loop for rows
         for (int i = iNum; i < numOfRows; i++) {
            //Inner loop for columns
            for (int j = jNum; j < numOfRows; j++) {
               //Reset variables and test queen
               canPlace = true;
               testQueen = new Queen(i, j);
               //Check if the current queen conflicts with any other current queens on the board
               for (int k = 0; k < board.size(); k++) {
                  if(testQueen.conflict(board.itemAt(k)))
                     canPlace = false;
               }
               //If the queen has no conflicts, we can place it on the board
               if (canPlace) {
                  //Push queen onto the board
                  board.push(testQueen); 
                  //Set j to the end of the row, since if we placed a queen on this row, we can move to the next
                  j = numOfRows - 1;
               }
            } 
            //Reset inner loop
            jNum = 0;      
         }

         //Check if the proper number of queens are on the board
         if (board.size() == numOfRows) {
            //If so, print out the solution
            System.out.print("Solution # " + solutionCounter + ": ");

            for (int k = board.size() - 1; k > -1; k--) {
               System.out.print("(" + board.itemAt(k) + ")  ");
            }
            System.out.println();
            //Increment solution counter
            solutionCounter++;
         }
         //pop a queen and move to the next spot
         testQueen = board.pop();
         //if we just popped a queen from the last column of the first row, there are no more possible solutions,
         //so we can break out of the loop and print the final tally
         if (testQueen.getRow() == 0 && testQueen.getColumn() == numOfRows - 1) 
            solutionFound = true;
         else {
            iNum = testQueen.getRow();
            jNum = testQueen.getColumn() + 1;
         }
      }
      //Print final results
      solutionCounter--;
      System.out.println();
      System.out.println("Number of solutions for a board of size (N = " + numOfRows + ") is " + solutionCounter + ".");
      System.out.println("Thanks for playing!");
   }
}