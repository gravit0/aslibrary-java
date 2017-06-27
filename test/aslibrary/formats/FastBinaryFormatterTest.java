/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aslibrary.formats;

import org.junit.*;

import static org.junit.Assert.assertArrayEquals;

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
        byte[] expResult = {0, 0, 0, 8, 0, 0, 0, 5, 0, 0, 0, 6, 72, 101, 108, 108, 111, 72, 101, 108, 108, 111, 50};
        byte[] result = FastBinaryFormatter4.encode(data);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of decode method, of class FastBinaryFormatter.
     */
    @Test
    public void testDecode() throws Exception {
        System.out.println("decode");
        byte[] orig = {0, 0, 0, 8, 0, 0, 0, 5, 0, 0, 0, 6, 72, 101, 108, 108, 111, 72, 101, 108, 108, 111, 50};
        byte[][] expResult = {"Hello".getBytes(),"Hello2".getBytes()};
        byte[][] result = FastBinaryFormatter4.decode(orig);
        for (byte[] v:result)
            System.out.write(v);

        assertArrayEquals(expResult, result);
    }
    
}
