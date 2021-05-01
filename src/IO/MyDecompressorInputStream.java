package IO;

import java.io.IOException;
import java.io.InputStream;


public class MyDecompressorInputStream extends InputStream {
    /** The verbals ot the class*/
    InputStream in;

    /**
     * @param in get an output stream and saved him
     */
    public MyDecompressorInputStream(InputStream in) {
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

    public int read(byte[] input) throws IOException {
        int k=0;
        int count=0;
        //The parameter to the helper function
        //step 1: Find and immediately insert the Limit_Row and LimitColumn Location to the input byte array
        while(count!=6) {
            input[k]=(byte) in.read();
            if(input[k]==-1)
                count++;
            k++;
        }
        int counter = 0;
        int num =0;
        String togoinbyte;
        byte temp;
        byte temp2;
        while(k!=input.length){
            temp = (byte) in.read();
            if(k > input.length-8){
                temp2 = (byte) in.read();
                num = temp;
                togoinbyte = intToBinary(num,temp2);
            }
            else{
                togoinbyte = intToBinary(8,temp);
            }
            for (int j = 0; j < togoinbyte.length(); j++) {
                input[k] = (byte)togoinbyte.charAt(j);
                if(input[k] == 49){
                     input[k] = 1;
                }
                else{
                     input[k] = 0;
                }
                k++;
            }

        }
        return 0;
    }


    private String intToBinary(int format, byte input){
        String stringFormat = "%"+format+"s";
        int newinput;
        if(input < 0){
            newinput = 128+(128-(-input));
        }else{
            newinput = input;
        }
        String result = Integer.toBinaryString(newinput);
        String resultWithPadding = String.format(stringFormat, result).replaceAll(" ", "0");
        return resultWithPadding;
    }

    /*private String intToBinary(byte input){
        int newinput;
        if(input < 0){
            newinput = 128+(128-(-input));
        }else{
            newinput = input;
        }
        String result = Integer.toBinaryString(newinput);
        String resultWithPadding = String.format("%8s", result).replaceAll(" ", "0");
        return resultWithPadding;
    }*/
}
