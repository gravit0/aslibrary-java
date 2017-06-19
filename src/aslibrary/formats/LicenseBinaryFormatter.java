package aslibrary.formats;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import aslibrary.util.BinaryHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;

/**
 *
 * @author gravit
 */
public class LicenseBinaryFormatter {
    public byte[] bytes = {};
    public byte[] headbytes = {};

    /**
     *
     */
    public LicenseBinaryFormatter() {
        
    }

    /**
     *
     * @param b
     * @throws IOException
     */
    public void add(byte[] b) throws IOException
    {
       ByteArrayOutputStream hout = new ByteArrayOutputStream();
       hout.write(headbytes);
       hout.write(b.length);
       headbytes = hout.toByteArray();
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       bout.write(bytes);
       bout.write(b);
       System.out.println("[A]"+String.valueOf(b.length));
       bytes = bout.toByteArray();
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public byte[] getBytes() throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(headbytes.length);
        out.write(headbytes);
        out.write(bytes);
        return out.toByteArray();
    }

    /**
     *
     * @param arr
     * @throws IOException
     */
    public LicenseBinaryFormatter(byte[] arr) throws IOException
    {
        ByteArrayInputStream in = new ByteArrayInputStream(arr);
        int lenhead = in.read();
        ByteArrayOutputStream hout = new ByteArrayOutputStream();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        for(int i=0;i<lenhead;i++)
        {
            hout.write(in.read());
        }
        headbytes = hout.toByteArray();
        for(int i=0;i<arr.length-lenhead-1;i++)
        {
            bout.write(in.read());
        }
        bytes = bout.toByteArray();
    }

    /**
     *
     * @param i
     * @return
     */
    public byte[] get(int i)
    {
        return get(i,0);
    }

    /**
     *
     * @param i
     * @param adding
     * @return
     */
    public byte[] get(int i,int adding)
    {
        int start = adding;
        for(int j=0;j<i;j++) {
            int len_tmp = headbytes[j];
            start = start+len_tmp;
        }
        System.out.println(start);
        int len = headbytes[i];
        System.out.println(len);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for(byte k=(byte) start;k<len+start;k++)
        {
            out.write(bytes[k]);
        }
        return out.toByteArray();
    }

    /**
     *
     * @param lng
     * @throws IOException
     */
    public void add(long lng) throws IOException {
        add(BinaryHelper.longToByteArray(lng));
    }
}
