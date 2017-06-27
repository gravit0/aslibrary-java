/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.formats;

import aslibrary.util.BinaryHelper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author gravit
 */
public class FastBinaryFormatter4 {

    /**
     *
     */
    public static final byte BYTES = Integer.BYTES;

    /**
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] encode(byte[][] data) throws IOException
    {
        int datalen = 0;
        for (byte[] v : data) {
            datalen+= v.length;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(1+datalen+data.length);
        out.write(BinaryHelper.IntToByteArray(data.length * BYTES));
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream(datalen);
        for (byte[] v : data) {
            out.write(BinaryHelper.IntToByteArray(v.length));
            try {
                bytesOut.write(v);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.write(bytesOut.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] arr =  out.toByteArray();
        return arr;
    }

    /**
     *
     * @param orig
     * @return
     * @throws IOException
     */
    public static Object[] decode(byte[] orig) throws IOException
    {
        if(orig.length<BYTES) throw new IllegalArgumentException("length < 4");
        ArrayList<byte[]> arr = new ArrayList<>();
        int headSize = BinaryHelper.byteArrayToInt(orig);
        if (headSize <= 0) throw new IllegalArgumentException("head is empry | head invalid");
        int bytes_starter = headSize + BYTES;
        if (orig.length - bytes_starter <= 0) throw new IllegalArgumentException("data is empry");
        int elm_col = headSize / BYTES;
        int bytesIterator = bytes_starter;
        for (int i = 0; i < elm_col; i++) {
            int len = BinaryHelper.byteArrayToInt(orig,BYTES+i*BYTES,4);
            byte[] data = Arrays.copyOfRange(orig, bytesIterator, bytesIterator + len);
            bytesIterator += len;
            arr.add(data);
        }
        return arr.toArray();
    }
}
