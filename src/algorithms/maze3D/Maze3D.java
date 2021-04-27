package algorithms.maze3D;

public class Maze3D {
    private int[][][] map;
    private Position3D startPosition;
    private Position3D goalPosition;

    /**
     * creating an instance of a 3D maze.
     * @param start
     * @param goal
     * @param mazeStruct
     */
    public Maze3D(Position3D start, Position3D goal, int[][][] mazeStruct) {
        this.map = mazeStruct;
        startPosition = start;
        goalPosition = goal;
    }

    /**
     * GETTERS:
     */
    public int[][][] getMap(){
        return this.map;
    }

    public Position3D getStartPosition(){
        return this.startPosition;
    }

    public Position3D getGoalPosition(){
        return this.goalPosition;
    }

    /**
     * prints the 3D maze.
     */
public void print(){
    System.out.println("{");
    for(int depth = 0; depth < map.length; depth++){
        for(int row = 0; row < map[0].length; row++) {
            System.out.print("{ ");
            for (int col = 0; col < map[0][0].length; col++) {
                if (depth == startPosition.getDepthIndex() && row == startPosition.getRowIndex() && col == startPosition.getColumnIndex()) // if the position is the start - mark with S
                    System.out.print("S ");
                else {
                    if (depth == goalPosition.getDepthIndex() && row == goalPosition.getRowIndex() && col == goalPosition.getColumnIndex()) // if the position is the goal - mark with E
                        System.out.print("E ");
                    else
                        System.out.print(map[depth][row][col] + " ");
                }
            }
            System.out.println("}");
        }
        if(depth < map.length - 1) {
            System.out.print("---");
            for (int i = 0; i < map[0][0].length; i++)
                System.out.print("--");
            System.out.println();
        }
    }
    System.out.println("}");
}

    /**
     * checks if the row and column given as params are legal within our grid
     * @param New_Depth
     * @param New_Row
     * @param New_column
     * @return boolean
     */
    public boolean IsLegal(int New_Depth,int New_Row,int New_column){
        int Border_Row=this.map[0].length;
        int Border_Column=this.map[0][0].length;
        int Border_Depth=this.map.length;
        return((New_Row<Border_Row)&&(New_column<Border_Column)&&(New_Depth<Border_Depth)&&
                (New_Row>=0)&&(New_column>=0)&&(New_Depth>=0)&&(this.map[New_Depth][New_Row][New_column]!=1));
    }

}
