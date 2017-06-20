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
        ByteArrayOutputStream bytesout = new ByteArrayOutputStream(datalen);
        for (byte[] v : data) {
            out.write(BinaryHelper.IntToByteArray(v.length));
            try {
                bytesout.write(v);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            out.write(bytesout.toByteArray());
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
        int headsize = (int) BinaryHelper.byteArrayToInt(orig);
        if (headsize <= 0) throw new IllegalArgumentException("head is empry | head invalid");
        int bytes_starter = headsize + BYTES;
        if (orig.length - bytes_starter <= 0) throw new IllegalArgumentException("data is empry");
        int elm_col = headsize / BYTES;
        int bytesiterator = bytes_starter;
        for (int i = 0; i < elm_col; i++) {
            int len = (int) BinaryHelper.byteArrayToInt(orig,BYTES+i*BYTES,4);
            byte[] data = Arrays.copyOfRange(orig, bytesiterator, bytesiterator + len);
            bytesiterator += len;
            arr.add(data);
        }
        return arr.toArray();
    }
}
