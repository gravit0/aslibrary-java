/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.util;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 *
 * @author gravit
 */
public class BinaryHelper {

    /**
     *
     * @param buff
     * @return
     */
    public static long byteArrayToLong(byte[] buff) {
        return byteArrayToLong(buff, 0, Long.BYTES);
    }
    //конвертация массива байтов в одиночный long

    /**
     *
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
     *
     * @param buff
     * @return byte[]
     */
    public static int byteArrayToInt(byte[] buff) {
        return byteArrayToInt(buff, 0, Integer.BYTES);
    }
    //конвертация массива байтов в одиночный long

    /**
     *
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
     *
     * @param value
     * @return
     * @throws IOException
     */
    public static byte[] longToByteArray(long value) throws IOException {

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
     *
     * @param value
     * @return
     * @throws IOException
     */
    public static byte[] IntToByteArray(int value) throws IOException {

        return new byte[]{
            (byte) (value >>> 24),
            (byte) (value >>> 16),
            (byte) (value >>> 8),
            (byte) value
        };
    }
}
