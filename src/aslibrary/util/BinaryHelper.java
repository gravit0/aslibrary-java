/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.util;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author gravit
 */
@SuppressWarnings("JavaDoc")
public class BinaryHelper {

    /**
     * @param buff
     * @return
     */
    public static long byteArrayToLong(byte[] buff) {
        return byteArrayToLong(buff, 0, Long.BYTES);
    }
    //конвертация массива байтов в одиночный long

    /**
     * @param buff
     * @param start_byte
     * @param stop_byte
     * @return
     */
    public static long byteArrayToLong(byte[] buff, int start_byte, int stop_byte) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(buff, start_byte, stop_byte);
        buffer.flip();//need flip 
        return buffer.getLong();
    }

    /**
     * @param buff
     * @return
     */
    public static byte[] invert(byte[] buff) {
        int center = buff.length / 2;
        byte tmp;
        for (int k = center; k >= 0; k--) {
            tmp = buff[k];
            buff[k] = buff[buff.length - k - 1];
            buff[buff.length - k - 1] = tmp;
        }
        return buff;
    }

    /**
     * @param arr1
     * @param arr2
     * @param start
     * @return
     */
    public static void concat(byte[] arr1, byte[] arr2, int start) {
        int size = arr2.length;
        for (int i = start; i < size; i++) {
            arr1[i] = arr2[i - start];
        }
    }

    /**
     * @param buff
     * @return byte[]
     */
    public static int byteArrayToInt(byte[] buff) {
        return byteArrayToInt(buff, 0, Integer.BYTES);
    }
    //конвертация массива байтов в одиночный long

    /**
     * @param buff
     * @param start_byte
     * @param len
     * @return int
     */
    public static int byteArrayToInt(byte[] buff, int start_byte, int len) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.put(buff, start_byte, len);
        buffer.flip();//need flip 
        return buffer.getInt();
    }

//конвертация одиночного long'а в массив byte

    /**
     * @param value
     * @return
     * @throws IOException
     */
    public static byte[] longToByteArray(long value) {

        return new byte[]{
                (byte) (value >>> 56),
                (byte) (value >>> 48),
                (byte) (value >>> 40),
                (byte) (value >>> 32),
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value
        };
    }

    /**
     * @param value
     * @return
     * @throws IOException
     */
    public static byte[] IntToByteArray(int value) {

        return new byte[]{
                (byte) (value >>> 24),
                (byte) (value >>> 16),
                (byte) (value >>> 8),
                (byte) value
        };
    }
}
