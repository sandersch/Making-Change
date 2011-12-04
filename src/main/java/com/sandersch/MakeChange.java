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

    public static List<Integer> greedy( int amount, List<Integer> coins )
    {
        List<Integer> results = new ArrayList<Integer>();
        for( Integer coin : coins )
        {
            int count = amount / coin;
            amount %= coin;
            for( int i = 0 ; i < count ; i++ ) { results.add( coin ); }
        }
        return results;
    }

    public static List<Integer> dynamic( int amount, List<Integer> coins )
    {
        Node root = new Node( null, 0, 0, 0 );
        Node[] solutions = new Node[amount+1];
        solutions[0] = root;
        for( int index = 1 ; index <= amount ; index++ )
        {
            solutions[index] = null;
        }
        Queue<Node> queue = new LinkedList<Node>(); 
        queue.add( root );

        while( !queue.isEmpty() )
        {
            Node node = queue.remove();
            for( int index = node.startingCoin ; index < coins.size() ; index++ )
            {
                int coin = coins.get(index);
                int newTotal = node.total + coin;  
        
                // Prune solutions that aren't useful since they are bigger
                // than our target or that we have already solved
                if( (newTotal > amount) || (solutions[newTotal] != null) )
                {
                    continue;
                }
               
                Node newNode = new Node( node, coin, newTotal, index );
                solutions[newTotal] = newNode;
                
                // Stop now if we've found our answer!
                if( newTotal == amount ) { break; }
                
                queue.add( newNode );
            }
        }

        // if we couldn't find a solution, return null
        if( null == solutions[amount] )
        {
            return null;
        }

        // Otherwise, unroll the solution and return that to the caller
        List<Integer> results = new ArrayList<Integer>();
        Node cursor = solutions[amount];
        while( 0 != cursor.coin )
        {
            results.add( cursor.coin );
            cursor = cursor.parent;
        }
        
        Collections.reverse( results );
        return results;

    }

    public static List<Integer> change( int amount, List<Integer> coins )
    {
        Collections.sort( coins, Collections.reverseOrder());
        List<Integer> result = dynamic( amount, coins );
        return result;
    }

    static class Node
    {
        public Node parent;
        public int coin, total, startingCoin;

        public Node( Node parent, int coin, int total, int startingCoin )
        {
            this.parent         = parent;
            this.coin           = coin;
            this.total          = total; 
            this.startingCoin   = startingCoin;
        }
    }
}
