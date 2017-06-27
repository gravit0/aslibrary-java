/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.formats;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author gravit
 */
public class FastBinaryFormatter1 {

    /**
     *
     */
    public static final byte BYTES = 1;

    /**
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] encode(byte[][] data) throws IllegalArgumentException
    {
        if (data.length > 255) throw new IllegalArgumentException("length > 255");
            int datalen = 0;
            for (byte[] v : data) {
                datalen+= v.length;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream(1+datalen+data.length);
            out.write(data.length);
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream(datalen);
            for (byte[] v : data) {
                if (v.length > 255) throw new IllegalArgumentException("length > 255");
                out.write(v.length);
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
     */
    public static Object[] decode(byte[] orig) throws IllegalArgumentException {
        if (orig.length < BYTES) throw new IllegalArgumentException("length < 1");
            ArrayList<byte[]> arr = new ArrayList<>();
            int headSize = Byte.toUnsignedInt(orig[0]);
            if (headSize <= 0) throw new IllegalArgumentException("head is empty | head invalid");
            int bytes_starter = headSize + BYTES;
            if (orig.length - bytes_starter <= 0) throw new IllegalArgumentException("data is empty");
            int elm_col = headSize / BYTES;
            int bytesIterator = bytes_starter;
            for (int i = 0; i < elm_col; i++) {
                int len = Byte.toUnsignedInt(orig[1+ i*BYTES]);
                byte[] data = Arrays.copyOfRange(orig, bytesIterator, bytesIterator + len);
                bytesIterator += len;
                arr.add(data);
            }
            return arr.toArray();
    }
}
