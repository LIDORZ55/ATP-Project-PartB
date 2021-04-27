
package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MyMazeGenerator extends AMazeGenerator{


    private int x;
    private int y;
    private Stack<Position> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;

    /**
     * The method returns an instance of Maze
     *
     * @param rows    -represent the row in the matrix
     * @param columns - represent the column in the matrix
     * @return a Maze
     */
    public Maze generate(int rows, int columns) throws Exception {
        /*** Check valid inputs */
        GeneralTest(rows, columns);

        int indicatorFinder = 0; //he will find the indicator that will tell us to put the ending point where the maze generation is (somewhat) close to the end
        maze = StartingMaze(rows,columns);
        int indicator = (int)((rows*columns)/2.2); // this variable will give us a close estimate to when the maze is close to the end where things get complicated
        stack.push(new Position(0,0));
        while (!stack.empty()) {
            Position next = stack.pop();
            if (validNextCell(next)) {
                maze[next.getRowIndex()][next.getColumnIndex()] = 0;
                indicatorFinder++;
                if(indicatorFinder == indicator ){
                    x = next.getRowIndex(); // x will represent the row index of the end of the maze
                    y = next.getColumnIndex(); // y will represent the col index of the end of the maze
                    indicator = 0;
                }
                ArrayList<Position> neighbors = locateNeighbors(next);
                randomlyAddCellsToTheStack(neighbors);
            }

        }
        return new Maze(maze,new Position(0,0), new Position(x, y));
    }

    /**
     * make the maze start with all the walls up.
     * @param rows
     * @param columns
     * @return maze with walls as int[][]
     */
    private int[][] StartingMaze(int rows, int columns){
        int [][]maze = new int[rows][columns];
        for (int i=0;i<maze.length;i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = 1;
            }
        }
        return maze;
    }

    /**
     * check if the cell has continuity to keep going or are we at a dead end (to not break walls we dont need to break) the number 3 is
     *      the key here because if there is more than 3 visited neighbors no matter which direction we take we will make a passage which is
     *          larger then a single line which will destroy the concept of a small(line) passage in the maze.
     * @param cellPos
     * @return
     */
    private boolean validNextCell(Position cellPos) {
        int visitedNeighbors = 0;
        for (int row = cellPos.getRowIndex()-1; row < cellPos.getRowIndex()+2; row++) {
            for (int col = cellPos.getColumnIndex()-1; col < cellPos.getColumnIndex()+2; col++) {
                if (validCell(row, col) && !sameCell(cellPos, row, col) && maze[row][col] == 0) {
                    visitedNeighbors++;
                }
            }
        }
        return (visitedNeighbors < 3) && maze[cellPos.getRowIndex()][cellPos.getColumnIndex()] != 0;
    }

    /**
     * randomly add cells from the list to the stack
     * @param cells
     */
    private void randomlyAddCellsToTheStack(ArrayList<Position> cells) {
        int index;
        while (!cells.isEmpty()) {
            index = rand.nextInt(cells.size());
            stack.push(cells.remove(index));
        }
    }

    /**
     * finding valid neighbors and adding them to the list
     * @param cellPos
     * @return
     */
    private ArrayList<Position> locateNeighbors(Position cellPos) {
        ArrayList<Position> neighbors = new ArrayList<>();
        for (int row = cellPos.getRowIndex()-1; row < cellPos.getRowIndex()+2; row++) {
            for (int col = cellPos.getColumnIndex()-1; col < cellPos.getColumnIndex()+2; col++) {
                if (validCell(row, col) && !isPointACorner(cellPos, row, col) && !sameCell(cellPos, row, col)) {
                    neighbors.add(new Position(row, col));
                }
            }
        }
        return neighbors;
    }

    /**
     * check if the current cell is the same cell we started looking neighboors for.
     * @param cellPos
     * @param row
     * @param col
     * @return boolean
     */
    private Boolean sameCell(Position cellPos, int row, int col) {
        if(row == cellPos.getRowIndex() && col == cellPos.getColumnIndex()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * check if the current cell is a corner neighbor of the cell we starting looking from.
     * @param cellPos
     * @param row
     * @param col
     * @return boolean
     */
    private Boolean isPointACorner(Position cellPos, int row, int col) {
        if(row != cellPos.getRowIndex() && col != cellPos.getColumnIndex()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * check if the cell is a valid cell inside the maze.
     * @param row
     * @param col
     * @return boolean
     */
    private Boolean validCell(int row, int col) {
        return row >= 0 && col >= 0 && row < this.maze.length && col < this.maze[0].length; // need to switch the arg and the sentence himself dont touch above.
    }




}