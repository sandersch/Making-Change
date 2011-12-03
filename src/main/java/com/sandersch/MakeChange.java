package com.sandersch;

import java.util.*;

/**
 * Hello world!
 *
 */
public class MakeChange 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public static List<Integer> change( int amount, List<Integer> coins )
    {
        Collections.sort( coins );
        Collections.reverse( coins );

        List<Integer> results = new ArrayList<Integer>();

        for( Integer coin : coins )
        {
            int count = amount / coin;
            amount %= coin;
            for( int i = 0 ; i < count ; i++ ) { results.add( coin ); }
        }

        return results;
    }
}
