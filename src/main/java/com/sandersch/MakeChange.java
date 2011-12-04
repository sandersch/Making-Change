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

    /**
     * Dynamic programming solution for change making.
     *
     * @param amount    The amount of change we need to find
     * @param coins     A sorted list of coins to use when forming the solution  
     *
     * @return          The coins that form the minimal solution for amount
     **/
    public static List<Integer> dynamic( int amount, List<Integer> coins )
    {
        // the root of our solutions tree is the zero coin solution
        // for making change for amount = 0
        Node root = new Node( null, 0, 0, 0 );
        // n-th entry in solutions array holds the leaf node of optimal 
        // solution for amount = n. 
        Node[] solutions = new Node[amount+1];
        solutions[0] = root;
        // This queue holds a worklist of partial solutions that we use to 
        // build larger solutions. We seed the list with the zero coin
        // solution and build out solutions from there
        Queue<Node> queue = new LinkedList<Node>(); 
        queue.add( root );

        // We process a queue of partial solutions and add a single coin
        // to each solution to build larger partial solutions that are added
        // to the queue until we find our desired solution for amount
        while( !queue.isEmpty() )
        {
            Node node = queue.remove();
            // Add coins to existing solution to find larger optimal solutions.
            // Start with the largest coin we haven't already ruled out
            for( int index = node.startingCoin ; index < coins.size() ; index++ )
            {
                int coin = coins.get(index);
                int newTotal = node.total + coin;  
        
                // Prune solutions that aren't useful since they are bigger
                // than our target amount or that we have already solved
                if( (newTotal > amount) || (solutions[newTotal] != null) )
                {
                    continue;
                }
              
                // This must me an optimal solution we haven't seen yet, so
                // add it to the list of solutions
                Node newNode = new Node( node, coin, newTotal, index );
                solutions[newTotal] = newNode;
                
                // Stop now if we've found our answer!
                if( newTotal == amount ) { break; }
               
                // Otherwise add the new solution to the worklist and continue
                // finding solutions
                queue.add( newNode );
            }
        }

        // if we couldn't find a solution, return null
        // XXX: is there something better to return here?
        if( null == solutions[amount] )
        {
            return null;
        }

        // Otherwise, walk the solutions tree to find our full solution
        List<Integer> results = new ArrayList<Integer>();
        Node cursor = solutions[amount];
        while( 0 != cursor.coin )
        {
            results.add( cursor.coin );
            cursor = cursor.parent;
        }
       
        // reverse the order of the results for we are in descending order
        // like the list of coins we used for this solution
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
