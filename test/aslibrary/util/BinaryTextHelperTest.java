/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.util;

import java.util.Arrays;

import aslibrary.util.binary.BinaryTextHelper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gravit
 */
public class BinaryTextHelperTest {
    
    public BinaryTextHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of IntToBytes method, of class BinaryTextHelper.
     */
    @Test
    public void testIntToBytes() {
        System.out.println("IntToBytes");
        int val = 12345;
        byte[] expResult = {'1','2','3','4','5'};
        byte[] result = BinaryTextHelper.IntToBytes(val);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of HextoByte2 method, of class BinaryTextHelper.
     */
    @Test
    public void testHextoByte2() {
        System.out.println("HexToByte byte[]");
        byte[] hexString = {'0','F','0','F'};
        byte[] expResult = {15,15};
        byte[] result = BinaryTextHelper.HexToByte(hexString);
        System.out.println(Arrays.toString(result));
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of HexToByte method, of class BinaryTextHelper.
     */
    @Test
    public void testHextoByte() {
        System.out.println("HexToByte String");
        String hexString = "0F0F";
        byte[] expResult = {15, 15};
        byte[] result = BinaryTextHelper.HexToByte(hexString);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of ByteToHex method, of class BinaryTextHelper.
     */
    @Test
    public void testBytetoHex() {
        System.out.println("ByteToHex");
        byte[] buf = {15, 15};
        byte[] expResult = {'0','F','0','F'};
        byte[] result = BinaryTextHelper.ByteToHex(buf);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
