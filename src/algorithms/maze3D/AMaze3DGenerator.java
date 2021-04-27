package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMaze3DGenerator {
    /** The method examining the time it took to create a maze
     * @param depth -represent the depth in the matrix
     * @param row -represent the row in the matrix
     * @param column- represent the column in the matrix
     * @return the time that took to create a maze
     * @throws Exception when the dimensions are invalid
     */
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int column) throws Exception {
        //we save the start time of the method
        long StartTime=System.currentTimeMillis();
        //we operate the generate method
        generate(depth,row,column);
        //we save the end time of the method
        long EndTime=System.currentTimeMillis();
        return EndTime-StartTime;
    }

    /**
     * Function that will help us throw an exception when wrong maze dimensions are entered
     * @param depth
     * @param row
     * @param column
     * @throws Exception when the dimensions are invalid
     */
    @Override
    public void GeneralTest(int depth, int row, int column) throws Exception {
        //Checking invalid input of the maze
        if(depth<=0||row<=0||column<=0)
            throw new Exception("Invalid input of row and column has enter!");
    }
}
