/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.util.binary;

import aslibrary.util.binary.BinaryHelper;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * @author gravit
 */
public class BinaryTextHelper {

    public static final byte[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static byte[] IntToBytes(int val) {
        if (val == Integer.MIN_VALUE) {
            return new byte[]{'-', '2', '1', '4', '7', '4', '8', '3', '6', '4', '8'};
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream(11);
        boolean invert = val < 0;
        if (invert) {
            val = -val;
        }
        int del = val; //Главный делитель
        while (del > 0) {
            int x = del % 10; //Остаток
            del = del / 10;
            out.write(NUMBERS[x]);
        }
        if (invert) {
            out.write('-');
        }
        return BinaryHelper.invert(out.toByteArray());
    }


    public static byte[] HexToByte(byte[] hexString) {
        int len = hexString.length / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            int r;
            byte[] tmp = Arrays.copyOfRange(hexString, 2 * i, 2 * i + 2);
            int num1, num2;
            if (tmp[0] < 'A') num1 = tmp[0] - NUMBERS[0];
            else num1 = tmp[0] - NUMBERS[10] + 10;
            if (tmp[1] < 'A') num2 = tmp[1] - NUMBERS[0];
            else num2 = tmp[1] - NUMBERS[10] + 10;
            r = (num1 << 4);
            r = r | num2;
            result[i] = (byte) r;
        }
        return result;
    }


    public static byte[] HexToByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        }
        return result;
    }


    public static byte[] ByteToHex(byte[] buf) {
        if (buf == null) throw new NullPointerException();
        ByteArrayOutputStream result = new ByteArrayOutputStream(2 * buf.length);
        for (byte aBuf : buf) {
            appendHex(result, aBuf);
        }
        return result.toByteArray();
    }

    private static void appendHex(ByteArrayOutputStream sb, byte b) {
        sb.write(NUMBERS[(b >> 4) & 0x0f]);
        sb.write(NUMBERS[b & 0x0f]);
    }
}
