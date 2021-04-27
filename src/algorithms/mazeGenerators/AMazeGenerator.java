package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    /** The method examining the time it took to create a maze
     * @param rows -represent the row in the matrix
     * @param columns- represent the column in the matrix
     * @return the time that took to create a maze
     * @throws Exception when the dimensions are invalid
     */
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) throws Exception {
        //we save the start time of the method
        long StartTime=System.currentTimeMillis();
        //we operate the generate method
        generate(rows,columns);
        //we save the end time of the method
        long EndTime=System.currentTimeMillis();
        return EndTime-StartTime;
    }

    /**
     * Function that will help us throw an exception when wrong maze dimensions are entered
     * @param row
     * @param column
     * @throws Exception when the dimensions are invalid
     */
    @Override
    public void GeneralTest(int row, int column) throws Exception {
        //Checking invalid input of the maze
        if(row<=0||column<=0)
            throw new Exception("Invalid input of row and column has enter!");


    }
}
