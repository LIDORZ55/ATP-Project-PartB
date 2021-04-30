package IO;
import algorithms.mazeGenerators.Position;
import java.util.ArrayList;
import java.io.IOException;
import java.io.OutputStream;
public class SimpleCompressorOutputStream extends OutputStream  {
    /** The verbals ot the class*/
    OutputStream out;

    /**
     * @param outputStream get an output stream and saved him
     */
    public SimpleCompressorOutputStream(OutputStream outputStream) {
        this.out = outputStream;

    }

    /**
     * Writes the specified byte to this output stream. The general
     * contract for {@code write} is that one byte is written
     * to the output stream. The byte to be written is the eight
     * low-order bits of the argument {@code b}. The 24
     * high-order bits of {@code b} are ignored.
     * <p>
     * Subclasses of {@code OutputStream} must provide an
     * implementation for this method.
     *
     * @param b the {@code byte}.
     * @throws IOException if an I/O error occurs. In particular,
     *                     an {@code IOException} may be thrown if the
     *                     output stream has been closed.
     */

    @Override
     public void write(int b) throws IOException {

    }

    public void write(byte [] b) throws IOException {

        //Represent the offset in the input array
        int k=0;
        int sum=0;
        int count=0;
        int Current_value=0;
        int LengthUntilData=0;
        //The parameter to the helper function
        int LimitRow, LimitColumn, Start_K=0;
    /**step 1: Find the Limit_Row and LimitColumn Location in the byte array */
        //step 1.a: and the Data location in the byte array.
        while(count!=6)
        {
            if(b[k]==-1)
                count++;
            k++;
        }
        LengthUntilData=k;
        /**step 2: Compress the whole Data array */
        byte [] DataArray=CompressByteArray(b,k);

        /**step 3: Moving the whole parameter(Start_Point,GoalPoint,LimitRow,LimitColumn +DataAfterCompress) to the new array */
        //Initializing the output array
        byte [] output=new byte[DataArray.length+LengthUntilData];
        //represent the offset in the output array
        int t=0;
        for (int i = 0; i <LengthUntilData ; i++) {
            //
            output[t]=b[i];
            t++;
        }
        for (int i = 0; i <DataArray.length ; i++) {
            output[t]=DataArray[i];
            t++;
        }


        //writing
        //this.out.write(output,0,output.length);
        for (int i = 0; i < output.length; i++) {
            this.out.write(output[i]);
        }
    }



     //Helper function:
    private byte [] CompressByteArray(byte [] input,int Start_K){


        ArrayList<Integer> output=new ArrayList<Integer>();
        int Count_0=0;
        int Count_1=0;
        int sum=0;
        int Flag_Which_Count=0;
        //represent the offset in the input array
        int k=Start_K;
        /**Step 1: Saving the Count 0 Values And the Count 1 Values in the next order: [**Count_0**],[**Count_1**] */
        //Step 1.a: Counting and Saving the values while lopping
        for (int i = k; i < input.length; i++) {
            //Case A: We are in 0 Counting
            if((input[i]==0) && (Flag_Which_Count==0))
                Count_0++;
            //Case B: we are in 1 Counting
            if((input[i]==1) && (Flag_Which_Count==1))
                Count_1++;
            //Case C:We moving from the 0 count to the 1 counting:
            if((input[i]==1) && (Flag_Which_Count==0)) {
                //Updating the current counting
                Flag_Which_Count = 1;
                Count_1++;
                //Saving the Count_0 Values:
                while (Count_0>255){
                    output.add(255);
                    output.add(0);
                    Count_0=Count_0-255;
                }
                output.add(Count_0);
                Count_0=0;
            }
            //Case D:We moving from the 1 count to the 0 counting:
            if((input[i]==0) && (Flag_Which_Count==1)) {
                //Updating the current counting
                Flag_Which_Count = 0;
                Count_0++;
                //Saving the Count_0 Values:
                while (Count_1>255){
                    output.add(0);
                    output.add(255);
                    Count_1=Count_1-255;
                }
                output.add(Count_1);
                Count_1=0;
            }
        }
        //Step 1.b: Saving the values after lopping
        //Case C:We moving from the 0 count to the 1 counting:
        if((Flag_Which_Count==0)) {
            //Saving the Count_0 Values:
            while (Count_0>255){
                output.add(255);
                output.add(0);
                Count_0=Count_0-255;
            }
            output.add(Count_0);
            Count_0=0;
        }
        //Case D:We moving from the 1 count to the 0 counting:
        if( (Flag_Which_Count==1)) {
            //Saving the Count_0 Values:
            while (Count_1>255){
                output.add(0);
                output.add(255);
                Count_1=Count_1-255;
            }
            output.add(Count_1);
            Count_1=0;
        }
        /**Step 2: convert to byte array: */
        byte array[] = new byte[output.size()];
        for (int i = 0; i < output.size(); i++) {
            array[i]=output.get(i).byteValue();
        }
        return array;

    }

}
