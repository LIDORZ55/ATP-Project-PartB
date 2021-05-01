package IO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SimpleDecompressorInputStream extends InputStream {
    /** The verbals ot the class*/
    InputStream in;

    /**
     * @param in get an output stream and saved him
     */
    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }

    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an {@code int} in the range {@code 0} to
     * {@code 255}. If no byte is available because the end of the stream
     * has been reached, the value {@code -1} is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * <p> A subclass must provide an implementation of this method.
     *
     * @return the next byte of data, or {@code -1} if the end of the
     * stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read() throws IOException {
        return 0;
    }

    /**
    /**
     * Reads the next byte of data from the input stream. The value byte is
     * returned as an {@code int} in the range {@code 0} to
     * {@code 255}. If no byte is available because the end of the stream
     * has been reached, the value {@code -1} is returned. This method
     * blocks until input data is available, the end of the stream is detected,
     * or an exception is thrown.
     *
     * <p> A subclass must provide an implementation of this method.
     *
     * @return the next byte of data, or {@code -1} if the end of the
     * stream is reached.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public int read(byte[] input) throws IOException {

        //Represent the offset in the input array
        int k=0;
        int sum=0;
        int count=0;
        int Current_value=0;
        int LengthUntilData=0;
        //The parameter to the helper function
        int LimitRow, LimitColumn, Start_K=0;
        //step 1: Find and immediately insert the Limit_Row and LimitColumn Location to the input byte array
        while(count!=6)
        {
            input[k]=(byte) in.read();
            if(input[k]==-1)
                count++;
            k++;
        }
        //step 2: Find and Convert  the Data array values
        //The order in which the values appears is: [_0_,_1_]
        //Each cell implement the Amount of the values 0,1
        LengthUntilData=k;
        int Flag_Which_Count=0;
        byte Current_Value=0;
        int Amount=0;
        while(k!=input.length){
            Current_Value=((byte) in.read());
            Amount=(int) Current_Value & 0xFF;
            for (int j = 0; j <Amount ; j++) {
                if(Flag_Which_Count==0){
                    input[k]=(byte) 0;
                }
                else {
                    input[k]=(byte) 1;
                }
                k++;
            }
            //Changing the Flag value
            if(Flag_Which_Count==0)
                Flag_Which_Count=1;
            else
                Flag_Which_Count=0;

        }
        return 0;
    }


}
