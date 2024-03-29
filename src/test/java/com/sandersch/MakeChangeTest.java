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
 
    public void testNonSolvableSolution()
    {
        assertEquals( null,
                      MakeChange.change( 7, Arrays.asList( 4, 2 )));
    }
    
    public void testNoChange()
    {
        assertEquals( Arrays.asList(),
                      MakeChange.change( 0, Arrays.asList( 4, 2 )));
    }

    public void testBigAmountWithBigCoin()
    {
        assertEquals( Arrays.asList( 1000000, 1 ),
                      MakeChange.change( 1000001, Arrays.asList( 1000000, 1 )));
        assertEquals( Arrays.asList( 10000000, 1 ),
                      MakeChange.change( 10000001, Arrays.asList( 10000000, 1 )));
        assertEquals( Arrays.asList( 100000000, 1 ),
                      MakeChange.change( 100000001, Arrays.asList( 100000000, 1 )));
        assertEquals( Arrays.asList( 1000000, 1 ),
                      MakeChange.change( 1000001, Arrays.asList( 1000000, 2, 1 )));
    }

    public void testShouldNotBeNaive()
    {
        assertEquals( Arrays.asList( 8, 8, 8 ),
                      MakeChange.change( 24, Arrays.asList( 10, 8, 2 )));
        assertEquals( Arrays.asList( 9, 2 ),
                      MakeChange.change( 11, Arrays.asList( 10, 9, 2 )));
    }

    public void testShouldHaveGoodPruning()
    {
        assertEquals( Arrays.asList( 5, 5, 5, 2, 2 ),
                      MakeChange.change( 19, Arrays.asList( 5, 2, 1 )));
        assertEquals( Arrays.asList( 5, 5, 5, 5, 5, 5, 5, 2, 2 ),
                      MakeChange.change( 39, Arrays.asList( 5, 2, 1 )));
    }

    public void testPrimeNumberCoins()
    {
        List<Integer> primeMoney = Arrays.asList( 
            97, 89, 83, 79, 73, 71, 67, 61, 59, 53, 47, 43,
            41, 37, 31, 29,23, 19, 17, 13, 11, 7, 5, 3
        );

        assertEquals( Arrays.asList( 89, 7, 5 ),
                      MakeChange.change( 101, primeMoney ));
        assertEquals( Arrays.asList( 
            97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 
            97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 
            97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 89, 7, 5 ),
                      MakeChange.change( 4563, primeMoney ));
    }

    public void testWithCombinationTrick()
    {
        List<Integer> twoPowerMoney = new ArrayList<Integer>();
        for( int i = 1 ; i < 10 ; i++ )
        {
            twoPowerMoney.add( (int)(Math.pow( 2.0, i )));
        }

        assertEquals( null,
                      MakeChange.change( (int)(Math.pow( 2.0, 10 ) - 1), twoPowerMoney ));
    }


    public void testSwissPaperMoney()
    {
        List<Integer> swissPaperMoney = Arrays.asList(
                1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 );

        assertEquals( Arrays.asList( 1000, 500, 200, 50, 20, 10, 5, 2, 2 ),
                      MakeChange.change( 1789, swissPaperMoney ));
    }
}
