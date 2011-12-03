package com.sandersch;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

/**
 * Unit test for simple MakeChange.
 */
public class MakeChangeTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MakeChangeTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MakeChangeTest.class );
    }

    public void test59inAmerican()
    {
        assertEquals( Arrays.asList( 25, 25, 5, 1, 1, 1, 1 ),
                      MakeChange.change( 59, Arrays.asList(1, 5, 10, 25 )));
    }

    public void test39inAmerican()
    {
        assertEquals( Arrays.asList( 25, 10, 1, 1, 1, 1 ),
                      MakeChange.change( 39, Arrays.asList(1, 5, 10, 25 )));
    }
    
    public void testAlienMoney()
    {
        assertEquals( Arrays.asList( 7, 7 ),
                      MakeChange.change( 14, Arrays.asList(10, 7, 1 )));
    }
}
