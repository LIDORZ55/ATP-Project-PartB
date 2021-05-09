package algorithms.mazeGenerators;


import java.io.Serializable;
import java.util.ArrayList;

public class Maze implements Serializable {
    private int[][] mazeStruct;
    private Position Start;
    private Position Goal;


    /**
     * creating a maze instance
     * @param mazeStruct
     * @param start
     * @param goal
     */
    public Maze(int[][] mazeStruct, Position start, Position goal) {
        this.mazeStruct = mazeStruct;
        this.Start = start;
        this.Goal = goal;
    }
    /***Constructor of- PART B OF THE PROJECT***/
    public Maze(byte[]input){
        //Represent the offset in the input array
        int k=0;
        int sum=0;
        int Current_value=0;
        /**step 1:Convert+Separate The MAZE Start Point values:(byte StartRow,byte StartColumn) To (int StartRow,int StartColumn) */
        //step 1.a: Separate the int StartRow
        while(input[k]!=-1)
        {
            Current_value=(int) input[k] & 0xFF;
            sum=sum+Current_value;
            k++;
        }
        k++;
        int StartRow=sum;
        sum=0;
        //step 1.b: Separate the int StartColumn
        while(input[k]!=-1)
        {
            Current_value=(int) input[k] & 0xFF;
            sum=sum+Current_value;
            k++;
        }
        k++;
        int StartColumn=sum;
        sum=0;
        //1.c:Save the new StartPosition:
        this.Start=new Position(StartRow,StartColumn);

        /**step 2: Convert+Separate The MAZE Goal Point values:(byte GoalRow,byte GoalColumn) To (int GoalRow,int GoalColumn) */
        //step 2.a: Separate the int GoalRow
        while(input[k]!=-1)
        {
            Current_value=(int) input[k] & 0xFF;
            sum=sum+Current_value;
            k++;
        }
        k++;
        int GoalRow=sum;
        sum=0;
        //step 2.b: Separate the int GoalColumn
        while(input[k]!=-1)
        {
            Current_value=(int) input[k] & 0xFF;
            sum=sum+Current_value;
            k++;
        }
        k++;
        int GoalColumn=sum;
        sum=0;
        //2.c:Save the new GoalPosition:
        this.Goal=new Position(GoalRow,GoalColumn);


        /**step 3:Convert The MAZE LIMITS (byte LimitRow,byte LimitColumn) to (int LimitRow,int LimitColumn) */
        //step 2.a: Separate the LimitRow
        while(input[k]!=-1)
        {
            Current_value=(int) input[k] & 0xFF;
            sum=sum+Current_value;
            k++;
        }
        k++;
        int LimitRow=sum;
        sum=0;
        //step 2.b: Separate the int LimitColumn
        while(input[k]!=-1)
        {
            Current_value=(int) input[k] & 0xFF;
            sum=sum+Current_value;
            k++;
        }
        k++;
        int LimitColumn=sum;
        sum=0;

        /**step 3:Convert The MAZE Data Byte- to the maze array int */
        this.mazeStruct=ConvertByteArrayToIntArray(input,LimitRow,LimitColumn,k);

    }

    /**
     * @return startPosition
     */
    public Position getStartPosition() {
        return this.Start;
    }

    /**
     * @return goalPosition
     */
    public Position getGoalPosition() {
        return this.Goal;
    }


    /**
     * prints the maze
     */
    public void print() {
        for (int i=0;i<this.mazeStruct.length;i++) {
            System.out.print("{ ");
            for (int j = 0; j < this.mazeStruct[0].length; j++) {
                if(i == this.Start.getRowIndex() && j == this.Start.getColumnIndex()){
                    System.out.print("S ");
                }
                else if (i == this.Goal.getRowIndex() && j == this.Goal.getColumnIndex()){
                    System.out.print("E ");
                }
                else{
                    if(this.mazeStruct[i][j] == 1 )
                    {
                        System.out.print("1 ");
                    }
                    else{
                        System.out.print(this.mazeStruct[i][j] + " ");
                    }
                }

            }
            System.out.print("}");
            System.out.println();
        }
    }

    /**
     * @return num of rows
     */
    public int GetRow() {
        return this.mazeStruct.length;
    }

    /**
     * @return num of columns
     */
    public int GetColumn() {
        return this.mazeStruct[0].length;
    }




/*
    public Map<Position,Integer > getPossiblePosition(Position position){ // delete prob
        Map<Position, Integer> map = new HashMap<>();
        int Cur_row=position.getRowIndex();
        int Cur_column=position.getColumnIndex();
        int New_Row,New_column=0;
        int Step2_Row,Step2_column=0;
        Boolean IsLegal_Upper,IsLegal_Lower,IsLegal_Right,IsLegal_Left=false;

        */
/****Upper-(1,1)->(0,1)****//*

        New_Row=Cur_row-1;
        New_column=Cur_column;
        IsLegal_Upper=this.IsLegal(New_Row,New_column);
        if(IsLegal_Upper==true)
            map.put(new Position(New_Row,New_column),10);

        */
/****Lower-(1,1)->(2,1)****//*

        New_Row=Cur_row+1;
        New_column=Cur_column;
        IsLegal_Lower=this.IsLegal(New_Row,New_column);
        if(IsLegal_Lower==true)
            map.put(new Position(New_Row,New_column),10);

        */
/****Left-(1,1)->(2,1)****//*

        New_Row=Cur_row;
        New_column=Cur_column-1;
        IsLegal_Left=this.IsLegal(New_Row,New_column);
        if(IsLegal_Left==true)
            map.put(new Position(New_Row,New_column),10);

        */
/****Right-(1,1)->(0,1)****//*

        New_Row=Cur_row;
        New_column=Cur_column+1;
        IsLegal_Right=this.IsLegal(New_Row,New_column);
        if(IsLegal_Right==true)
            map.put(new Position(New_Row,New_column),10);


        //Diagonal down right-(1,1)->(2,2)
        New_Row=Cur_row+1;
        New_column=Cur_column+1;
        if((this.IsLegal(New_Row,New_column)==true)&&(IsLegal_Right==true))
            map.put(new Position(New_Row,New_column),15);



        //Diagonal top right-(1,1)->(0,2)
        New_Row=Cur_row-1;
        New_column=Cur_column+1;
        //Upping+Right IsLegal Checking
        if((this.IsLegal(New_Row,New_column)==true)&&(IsLegal_Upper==true))
            map.put(new Position(New_Row,New_column),15);

        //Diagonal top left-(1,1)->(0,0)
        New_Row=Cur_row-1;
        New_column=Cur_column-1;
        if((this.IsLegal(New_Row,New_column)==true)&&(IsLegal_Upper==true))
            map.put(new Position(New_Row,New_column),15);


        //Diagonal down left-(1,1)->(2,0)
        New_Row=Cur_row+1;
        New_column=Cur_column-1;
        if((this.IsLegal(New_Row,New_column)==true)&&(IsLegal_Left==true))
            map.put(new Position(New_Row,New_column),15);
        return map;
    }
*/


    /**
     * checks if the row and column given as params are legal within our grid
     * @param New_Row
     * @param New_column
     * @return boolean
     */
public boolean IsLegal(int New_Row,int New_column ){
    int Border_Row=this.mazeStruct.length;
    int Border_Column=this.mazeStruct[0].length;
    return((New_Row<Border_Row)&&(New_column<Border_Column)&&
            (New_Row>=0)&&(New_column>=0)&&(this.mazeStruct[New_Row][New_column]!=1));
    }
    /***PART B OF THE PROJECT***/
//Helper function 1:
    public byte[] ConvertIntToByteArray(int number){
        //step1:calculate the number between 0-254:
        ArrayList<Integer> output=new ArrayList<Integer>();
        while (number>254){
            output.add(254);
            number=number-254;

        }
        output.add(number);
        //step 2:convert to byte array:
        byte array[] = new byte[output.size()];
        for (int i = 0; i < output.size(); i++) {
            array[i]=output.get(i).byteValue();
        }
        return array;

    }
    //Helper function 2
    public byte[] ConvertIntArrayToByteArray(int array[][]){
        byte output[] = new byte[(array.length)*(array[0].length)];
        //represent the offset in the output array
        int k=0;
        for (int i=0;i<array.length;i++) {
            for (int j = 0; j < array[0].length; j++) {
                    output[k]=(byte)array[i][j];
                    k++;
            }
        }
            return output;
   }
   //Helper Function 3:
    public int[][]ConvertByteArrayToIntArray(byte[] input,int LimitRow,int LimitColumn,int Start_K){
        int[][] Output=new int[LimitRow][LimitColumn];
        //represent the offset in the output array
        int k=Start_K;
        for (int i = 0; i < LimitRow; i++) {
            for (int j = 0; j < LimitColumn; j++) {
                Output[i][j]=input[k];
                k++;
            }
        }
        return Output;

    }


    /**
     * @return the byte array[] who represent the whole DATA about the Maze:
     * each cell has a value between [-127,128]
     * the last byte: 255->[-1] use as flag!-(meaning that this number separate between the another verbals!)
     * in this order the verbals of Maze Arranged:
     * 1.The MAZE Start Point values:(byte StartRow,byte StartColumn)
     * 2.The Maze Goal Point values:(byte GoalRow,byte GoalColumn)
     * 3.The MAZE LIMITS (byte LimitRow,byte LimitColumn)
     * 4.The array [] [] Data.
     * For example:[1.a(byte StartRow),1.b(byte StartColumn),2.a(byte StartRow),2.b(byte StartColumn),3.a(byte LimitRow),3.b(byte LimitColumn),Data]
     */
    public byte[] toByteArray(){
        /**1.The MAZE Start Point values:(byte StartRow,byte StartColumn)  */
        //1.a. (byte StartRow).[254,12]-represent the 256(int)
        byte [] StartRow_B=ConvertIntToByteArray(Start.getRowIndex());
        //1.b. (byte StartColumn)
        byte [] StartColumn_B=ConvertIntToByteArray(Start.getColumnIndex());

        /**2.The Maze Goal Point values:(byte StartRow,byte StartColumn) */
        //2.a (byte GoalRow)
        byte [] GoalRow_B=ConvertIntToByteArray(Goal.getRowIndex());
        //2.b (byte GoalColumn)
        byte [] GoalColumn_B=ConvertIntToByteArray(Goal.getColumnIndex());

        /**3.The MAZE LIMITS (byte LimitRow,byte LimitColumn)*/
        //3.a (byte LimitRow)
        byte [] LimitRow_B=ConvertIntToByteArray(this.mazeStruct.length);
        //3.b (byte LimitColumn)
        byte [] LimitColumn_B=ConvertIntToByteArray(this.mazeStruct[0].length);

        /**4.The maze Data**/
        byte [] Data_B=ConvertIntArrayToByteArray(mazeStruct);
        //We calculate the size of the Output array
        int Output_size=StartRow_B.length+StartColumn_B.length+GoalRow_B.length+GoalColumn_B.length+LimitRow_B.length+LimitColumn_B.length+Data_B.length+6;
        byte[] Output=new byte[Output_size];
        //represent the offset in the output array
        int k=0;
        //step 1:connect The MAZE Start Point values to the new output array
        for (int i = 0; i < StartRow_B.length; i++) {
            Output[k]=StartRow_B[i];
            k++;
        }
        //(*)Separate with the flag(The Value [-1]-> 255):
        Output[k]=-1;
        k++;
        for (int i = 0; i < StartColumn_B.length; i++) {
            Output[k]=StartColumn_B[i];
            k++;
        }
        //(**)Separate with the flag(The Value [-1]-> 255):
        Output[k]=-1;
        k++;
        //Step 2: connect the Maze Goal Point values to the new output array:
        for (int i = 0; i < GoalRow_B.length; i++) {
            Output[k]=GoalRow_B[i];
            k++;
        }
        //(***)Separate with the flag(The Value [-1]-> 255):
        Output[k]=-1;
        k++;
        for (int i = 0; i < GoalColumn_B.length; i++) {
            Output[k]=GoalColumn_B[i];
            k++;
        }
        //(****)Separate with the flag(The Value [-1]-> 255):
        Output[k]=-1;
        k++;
        //Step 3: Connect the Maze Limit to the new Output array:
        for (int i = 0; i < LimitRow_B.length; i++) {
            Output[k]=LimitRow_B[i];
            k++;
        }
        //(*****)Separate with the flag(The Value [-1]-> 255):
        Output[k]=-1;
        k++;
        for (int i = 0; i < LimitColumn_B.length; i++) {
            Output[k]=LimitColumn_B[i];
            k++;
        }
        //(******)Separate with the flag(The Value [-1]-> 255):
        Output[k]=-1;
        k++;
        //Step 4:Connect the MazeStructure to the new Output array
        byte array[]=ConvertIntArrayToByteArray(mazeStruct);
        for (int i = 0; i < array.length; i++) {
            Output[k]=array[i];
            k++;
        }
        return Output;

    }

}
