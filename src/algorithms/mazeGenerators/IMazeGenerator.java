
package algorithms.mazeGenerators;

public interface IMazeGenerator {
    /** The method returns an instance of Maze
     * @param rows -represent the row in the matrix
     * @param columns- represent the column in the matrix
     * @return a Maze
     */

    Maze generate(int rows,int columns) throws Exception;

    /** The method examining the time it took to create a maze
     * @param rows -represent the row in the matrix
     * @param columns- represent the column in the matrix
     * @return the time that took to create a maze
     */
    long measureAlgorithmTimeMillis(int rows,int columns) throws Exception;

    /**
     *
     * @param row
     * @param column
     */
    void GeneralTest(int row,int column) throws Exception;
}