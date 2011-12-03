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

    public void testMakeChange()
    {
        testMakesCorrectChange( 39, Arrays.asList( 1, 5, 10, 25 ), 
                                Arrays.asList( 25, 10, 1, 1, 1, 1 )); 
    }

    public void testMakesCorrectChange( int amount, List<Integer> coins, List<Integer> rightAnswer )
    {
        List<Integer> result = MakeChange.change( amount, coins );
        assertEquals( result, rightAnswer );
    }
}
