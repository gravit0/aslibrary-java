/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.formats;

import aslibrary.FormatException;
import aslibrary.util.BinaryHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author gravit
 */
public class FastBinaryFormatter1 {

    public static final byte BYTES = 1;


    public static byte[] encode(byte[][] data) throws FormatException {
        if (data.length > 255) throw new FormatException("length > 255", "FastBinary");
        int lengthData = 0;
        for (byte[] v : data) {
            lengthData += v.length;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(BYTES + lengthData + data.length);
        out.write(data.length);
        byte[] bytes = new byte[lengthData + 1];
        int bytesIterator = 0;
        for (byte[] v : data) {
            if (v.length > 255) throw new FormatException("data length > 255", "FastBinary");
            out.write(v.length);
            BinaryHelper.concat(bytes, v, bytesIterator);
            bytesIterator += v.length;
        }
        try {
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static byte[][] decode(byte[] orig) throws FormatException {
        if (orig.length < BYTES) throw new FormatException("length < 1", "FastBinary");
        int headSize = Byte.toUnsignedInt(orig[0]);
        byte[][] arr = new byte[headSize][];
        int arrIterator = 0;
        if (headSize <= 0) throw new FormatException("head is empty | head invalid", "FastBinary");
        int bytes_starter = headSize + BYTES;
        if (orig.length - bytes_starter <= 0) throw new FormatException("data is empty", "FastBinary");
        int elm_col = headSize / BYTES;
        int bytesIterator = bytes_starter;
        for (int i = 0; i < elm_col; i++) {
            int len = Byte.toUnsignedInt(orig[1 + i * BYTES]);
            byte[] data = Arrays.copyOfRange(orig, bytesIterator, bytesIterator + len);
            bytesIterator += len;
            arr[arrIterator] = data;
            arrIterator++;
        }
        return arr;
    }
}
