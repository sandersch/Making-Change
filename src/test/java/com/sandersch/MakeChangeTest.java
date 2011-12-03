package com.sandersch;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
        assertTrue( true );
    }
}
