package algorithms.test;

import java.util.ArrayList;
public class GenralBytetest {

    public static byte[] ConvertIntToByteArray(int number){
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
    public static void main(String args[])
    {
        /**The MAZE LIMITS (int LimitRow,int LimitColumn)*/
        int LimitRow=74;
        int LimitColumn=0;
        /**The MAZE_Start Point values:(int StartRow,int StartColumn)  */
        int StartRow=0;
        int StartColumn=0;
        /**The MAZE_Goal Point values:(int StartRow,int StartColumn)  */
        int GoalRow=0;
        int GoalColumn=0;
        /** The maze Data**/
        //array of 0 and 1 -each cell is a byte
        /***/


        //the values in byte: [-128 ,127]
        //I can use the numbers directly in the array: 0-254.
        //The Formula is: (integer number)-256=Byte number between the values: [-128 ,127]
        //need to obtain between the different-each number not write directly:
        /**
         * 0->[0]
         * 1->[1]
         * ...
         * 127->[127]
         * 128->[-128]
         * 129->[-127]
         * 130->[-126]
         * ...
         * 254->[-2]
         * 255->[-1]--The flag.
         * 256->[0]-- cycling.
         * */

        //0 in the array represent in the start the number 0,and in the data is represent the opposite of wall
        //1 in the array represent in the start the number 1,and in the data is represent the wall
        //the last byte: 255 use as flag!-(meaning that this number separate between the another verbals!)
        //for example, the number 380 in my solution will represent like this:[254,254,47]

        //two ways to convert int to byte:
        //the first-using Integer Object
        Integer y=680;
        byte x= y.byteValue();
        //The second:
        int i = 555;
        byte b = (byte) i;
        System.out.println(b); // -22
        int i2 = b & 0xFF;
        System.out.println(i2);

        ///
        byte array[]=ConvertIntToByteArray(1000);


    }


}
