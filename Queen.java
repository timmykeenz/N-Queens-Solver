
/******************************************************************************
* A <CODE>Queen</CODE> is an object representing the Queen piece in a standard
* chess set. Queens are a piece which can attack any number of spaces along
* the row, column, and both diagonals on which they are placed.
*
* This class was written for CSC 103 Data Structures, Spring 2019, Project 3,
* in which a brute-force solution to the N-Queens Problem was implemented
* using a LinkedStack ADT.
*
* @author Daniel Breslawski, Timothy Keene
*
******************************************************************************/
public class Queen {
   
   //Invariant of the Queen class:
   //The Queen class represents the Queen piece in a standard chess set.
   //It can attack along rows, columns, and diagonals as is standard.
   //Its data members represent the row and column of the board on which it is placed.
   //Row and Column have a minimum of 0 for any size board and a max of (n-1) for an n-sized board.
   private int row;
   private int column;
   
   //Constructors

   /** 
    * Empty constructor: Initialize a Queen to row 0 and column 0.
    * @param - none
    * <dt><b>Postcondition:</b><dd>
    *    This Queen is at the corner of the board, i.e. row 0 and column 0.
    */
   public Queen() 
   {
      this(0,0);
   }

   /**
    * Initialize a Queen at the specified row and column between 0 and n-1 inclusive,
    * where n is the board size.
    * @param setRow
    *    The row in which this Queen is placed.
    * @param setColumn
    *    The column in which this Queen is placed.
    * @precondition
    *    The arguments passed into this Queen must be positive integers.
    * @postcondition
    *    A queen has been created and is placed on the board at row setRow and column setColumn.
    * @exception IllegalStateException
    *    Indicates that a negative number was passed into this constructor.
    */
   public Queen(int setRow, int setColumn)
   {
      if (setRow < 0 || setColumn < 0)
         throw new IllegalStateException("The row and column must be positive numbers.");
         
      row = setRow;
      column = setColumn;
   }
   
   //Accessors

   /**
    * Accessor method to get the row that this Queen is placed in, between 0 and (n-1) inclusive.
    * @param - none
    * @return 
    *    An integer representing the row in which this Queen is placed.
    */
   public int getRow() 
   {
      return row;
   }
   /**
    * Accessor method to get the column that this Queen is placed in, between 0 and (n-1) inclusive.
    * @param - none
    * @return 
    *    An integer representing the column in which this Queen is placed.
    */
   public int getColumn() 
   {
      return column;
   }
   
   //Mutators

   /**
    * Move this Queen to the specified row.
    * @param setRow
    *    The row to which we would like to move this Queen, between 0 and (n-1) inclusive.
    * @precondition
    *    The parameter setRow must be a non-negative integer.
    * @postcondition
    *    This Queen has been moved to the specified row on the board.
    * @throws IllegalStateException
    *    Indicates that a negative integer was passed into the method.
    */
   public void setRow(int setRow) 
   {
      if (setRow < 0)
         throw new IllegalStateException("The row must be a positive number.");
         
      row = setRow;
   }
   /**
    * Move this Queen to the specified column.
    * @param setColumn
    *    The column to which we would like to move this Queen, between 0 and (n-1) inclusive.
    * @precondition
    *    The parameter setColumn must be a non-negative integer.
    * @postcondition
    *    This Queen has been moved to the specified column on the board.
    * @throws IllegalStateException
    *    Indicates that a negative integer was passed into the method.
    */
   public void setColumn(int setColumn)
   {
      if (setColumn < 0)
         throw new IllegalStateException("The column must be a positive number.");
         
      column = setColumn;
   }
   
   /**
    * Tell whether or not two Queens are in conflict; i.e. if they can attack each other 
    * by standard Chess rules. A Queen may attack on any number of spaces along the row, column,
    * or diagonal that it is placed on.
    * @param targetQueen
    *    The queen against which we are checking this Queen for conflict.
    * @precondition
    *    Both queens must be initialized; i.e. they must be placed on the board to check for conflicts.
    * @return
    *    A boolean is returned representing whether the passed-in Queen and the acted-on Queen
    *    can attack each other in their current positions.
    * @postcondition
    *    True is returned if the Queens are in conflict. If not, False is returned.
    * @throws IllegalStateException
    *    Indicates that either Queen has been declared but not initialized. Since we cannot check conflict
    *    unless both Queens are on the board, a boolean return in this case would be meaningless. 
    */
   public boolean conflict(Queen targetQueen)
   {
      boolean isConflict = false;
      int deltaRow;
      int deltaColumn;

      if (this == null || targetQueen == null) 
         throw new IllegalStateException("One of the Queens has not been placed on the board. Please place it and try again.");
      
      //Check if the other queen is in the same row or column
      if (this.row == targetQueen.getRow() || this.column == targetQueen.getColumn())
         isConflict = true;
      
      //Check if the queen is on the same diagonal by checking equality between
      //the difference in rows and difference in columns
      deltaRow = Math.abs(this.row - targetQueen.getRow());
      deltaColumn = Math.abs(this.column - targetQueen.getColumn());
      if (deltaRow == deltaColumn)
         isConflict = true;
         
      return isConflict;
   }
   
   /**
    * Returns a string representation in form "row = x, column = y".
    * The values returned by this string representation are shifted up 1 from the data members
    * of the Queen in order to represent values between 1 and n, inclusive, instead of 0 and (n-1).
    * This makes it more parsable by end users.
    * @param - none
    * @return
    *    A string representing where this Queeen is on the board.
    * @postcondition
    *    A String is returned containing the values between 1 and n of this Queen's position on the board.
    */
   public String toString() {
      return ("row = " + (row + 1) + ", column = " + (column + 1));
   }
}