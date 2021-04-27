package algorithms.mazeGenerators;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    /**
     * The method returns an instance of Maze
     *
     * @param rows    -represent the row in the matrix
     * @param columns - represent the column in the matrix
     * @return a Maze
     */
    @Override
    public Maze generate(int rows, int columns) throws Exception {
        /*** Check valid inputs */
        GeneralTest(rows, columns);
        int[][]maze = new int[rows][columns];
        Random rnd = new Random();
        for (int i=0;i<maze.length;i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = rnd.nextInt(2);
            }
        }
        for (int i=0;i<maze.length;i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if(i == 0){
                    maze[i][j] = 0;
                }
                if(j == columns-1){
                    maze[i][j] = 0;
                }
            }
        }
        return new Maze(maze,new Position(0,0),new Position(rows-1,columns-1));
    }
}