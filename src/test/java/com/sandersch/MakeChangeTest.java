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

    /**
     * Rigourous Test :-)
     */
    public void testMakeChange()
    {
        Integer[] coins = { 1, 5, 10, 25 }; 
        Integer[] rightAnswer = { 25, 10, 1, 1, 1, 1, }; 

        List<Integer> result = MakeChange.change( 39, Arrays.asList( coins ));
        assertTrue( result.equals( Arrays.asList( rightAnswer )));
    }
}
