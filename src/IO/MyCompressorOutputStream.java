package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class MyCompressorOutputStream extends OutputStream {

    /** The verbals ot the class*/
    OutputStream out;

    /**
     * @param outputStream get an output stream and saved him
     */
    public MyCompressorOutputStream(OutputStream outputStream) {
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

    public void write(byte[] b) throws IOException{
        //Represent the offset in the input array
        int k=0;
        int count=0;
        int LengthUntilData=0;
        //The parameter to the helper function
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
        for (int i = 0; i < output.length; i++) {
            this.out.write(output[i]);
        }

    }
    //Helper function:
    private byte[] CompressByteArray(byte [] input,int Start_K){
        ArrayList<Integer> output=new ArrayList<Integer>();
        int counter = 0;
        String binarynumber = "";
        for (int i = Start_K; i < input.length; i++) {
            binarynumber += input[i];
            counter++;
            if(counter == 8){
                counter = 0;
                output.add(ConvertBinaryToInt(binarynumber));
                binarynumber = "";
            }

            if(i == input.length-1 && counter != 0){
                output.add(counter);
                output.add(ConvertBinaryToInt(binarynumber));
            }


        }
        /**Step 2: convert to byte array: */
        byte array[] = new byte[output.size()];
        for (int i = 0; i < output.size(); i++) {
            array[i]=output.get(i).byteValue();
        }
        return array;

    }

    private int ConvertBinaryToInt(String binarynumber){
        int decimal = Integer.parseInt(binarynumber,2);
        return decimal;
    }
}
