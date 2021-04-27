package algorithms.maze3D;

public interface IMaze3DGenerator {
    Maze3D generate(int Depth, int row, int column) throws Exception;
    long measureAlgorithmTimeMillis(int depth, int row, int column) throws Exception;
    void GeneralTest(int depth,int row,int column) throws Exception;
}
