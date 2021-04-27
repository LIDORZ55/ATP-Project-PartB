package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{

    /**
     * generating an empty maze with given dimensions
     * @param rows     -represent the row in the matrix
     * @param columns- represent the column in the matrix
     * @return new Maze
     * @throws Exception
     */
    @Override
    public Maze generate(int rows, int columns) throws Exception  {
        GeneralTest(rows, columns);

        int[][]a = new int[rows][columns];
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                a[i][j] = 0;
            }
        }
        return new Maze(a,new Position(0,0), new Position(rows-1,columns-1));
    }
}
