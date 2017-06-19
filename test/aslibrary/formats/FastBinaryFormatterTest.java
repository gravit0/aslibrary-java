/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.formats;

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
public class FastBinaryFormatterTest {
    
    public FastBinaryFormatterTest() {
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
     * Test of encode method, of class FastBinaryFormatter.
     */
    @Test
    public void testEncode() throws Exception {
        System.out.println("encode");
        byte[][] data = {"Hello".getBytes(),"Hello2".getBytes()};
        FastBinaryFormatter4 instance = new FastBinaryFormatter4();
        byte[] expResult = {0, 0, 0, 8, 0, 0, 0, 5, 0, 0, 0, 6, 72, 101, 108, 108, 111, 72, 101, 108, 108, 111, 50};
        byte[] result = instance.encode(data);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of decode method, of class FastBinaryFormatter.
     */
    @Test
    public void testDecode() throws Exception {
        System.out.println("decode");
        byte[] orig = {0, 0, 0, 8, 0, 0, 0, 5, 0, 0, 0, 6, 72, 101, 108, 108, 111, 72, 101, 108, 108, 111, 50};
        FastBinaryFormatter4 instance = new FastBinaryFormatter4();
        Object[] expResult = {"Hello".getBytes(),"Hello2".getBytes()};
        Object[] result = instance.decode(orig);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
