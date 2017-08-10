/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.formats;

import aslibrary.util.BinaryHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author gravit
 */
@SuppressWarnings("JavaDoc")
public class FastBinaryFormatter4 {

    /**
     *
     */
    public static final byte BYTES = Integer.BYTES;

    /**
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] encode(byte[][] data) throws IOException {
        int lengthData = 0;
        for (byte[] v : data) {
            lengthData += v.length;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(1 + lengthData + data.length);
        out.write(BinaryHelper.IntToByteArray(data.length * BYTES));
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream(lengthData);
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
        return out.toByteArray();
    }

    /**
     * @param orig
     * @return
     * @throws IOException
     */
    public static byte[][] decode(byte[] orig) {
        if (orig.length < BYTES) throw new IllegalArgumentException("length < 4");
        int headSize = BinaryHelper.byteArrayToInt(orig);
        int arrIterator = 0;
        if (headSize <= 0) throw new IllegalArgumentException("head is empty | head invalid");
        int bytes_starter = headSize + BYTES;
        if (orig.length - bytes_starter <= 0) throw new IllegalArgumentException("data is empty");
        int elm_col = headSize / BYTES;
        byte[][] arr = new byte[elm_col][];
        int bytesIterator = bytes_starter;
        for (int i = 0; i < elm_col; i++) {
            int len = BinaryHelper.byteArrayToInt(orig, BYTES + i * BYTES, 4);
            byte[] data = Arrays.copyOfRange(orig, bytesIterator, bytesIterator + len);
            bytesIterator += len;
            arr[arrIterator] = data;
            arrIterator++;
        }
        return arr;
    }
}
