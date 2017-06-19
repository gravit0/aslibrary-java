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
    public byte[] encode(byte[][] data) throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayOutputStream headout = new ByteArrayOutputStream();
        ByteArrayOutputStream bytesout = new ByteArrayOutputStream();
        for(byte[] v : data)
        {
            headout.write(v.length);
            bytesout.write(v);
        }
        byte[] head = headout.toByteArray();
        byte[] bytes = bytesout.toByteArray();
        out.write(head.length);
        out.write(head);
        out.write(bytes);
        return out.toByteArray();
    }

    /**
     *
     * @param orig
     * @return
     * @throws IOException
     */
    public Object[] decode(byte[] orig) throws IOException
    {
        if(orig.length<BYTES*2) throw new IllegalArgumentException("length < 2");
        ArrayList<byte[]> arr = new ArrayList<>();
        byte[] head;
        byte[] bytes;
        int headsize = orig[0];
        head = Arrays.copyOfRange(orig, BYTES, headsize+BYTES);
        if(head.length<BYTES) throw new IllegalArgumentException("head is empry | head invalid");
        int bytes_starter = headsize+BYTES;
        bytes = Arrays.copyOfRange(orig, bytes_starter, orig.length);
        if(bytes.length == 0) throw new IllegalArgumentException("data is empry");
        int elems_col = headsize / BYTES;
        int bytesiterator = 0;
        for(int i = 0;i<elems_col;i++)
        {
            byte[] len_byte = Arrays.copyOfRange(head, i*BYTES, i*BYTES+BYTES);
            int len = len_byte[0];
            byte[] data = Arrays.copyOfRange(bytes, bytesiterator, bytesiterator+len);
            bytesiterator += len;
            arr.add(data);
        }
        return arr.toArray();
    }
}
