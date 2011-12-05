package com.sandersch;

import java.util.*;

/**
 * Hello world!
 *
 */
public class MakeChange 
{
    /**
     * Given an amount and some coins, find the minimal arrangement of
     * coins whose total equals amount
     *
     * @param amount    The amount of change that we need to find
     * @param coins     The coins we should use to find a solution
     *
     * @return          A list containing the minimal coins that total amount
     **/
    public static List<Integer> change( int amount, List<Integer> coins )
    {
        Collections.sort( coins, Collections.reverseOrder());
        List<Integer> result = dynamic( amount, coins );
        return result;
    }

    /**
     * One argument version of change that expects only a single argument
     * of an amount to make change for that defaults to using common American
     * coin denominations like the quiz specifies
     *
     * @param amount    The our target amount for making change
     *
     * @return          A list containing the minimal coins that total amount
     **/
    public static List<Integer> change( int amount )
    {
        return change( amount, Arrays.asList( 25, 10, 5, 1 ));
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
        Map<Integer,Node> solutions = new HashMap<Integer,Node>();
        solutions.put( 0, root );

        // This queue holds a worklist of partial solutions that we use to 
        // build larger solutions. We seed the list with the zero coin
        // solution and build out solutions from there
        Queue<Node> queue = new LinkedList<Node>(); 
        queue.add( root );

        // Process a queue of partial solutions and add a single coin
        // to each solution to build larger partial solutions that are added
        // to the queue until we find our desired solution
        while( !queue.isEmpty() && !solutions.containsKey( amount ) )
        {
            // Start with existing solution off the queue
            Node node = queue.remove();
          
            // Add coins to existing solution to find larger solutions.
            // Start with the largest coin we haven't already ruled out
            for( int index = node.startingCoin ; index < coins.size() ; index++ )
            {
                int coin = coins.get(index);
                int newTotal = node.total + coin; // new solution 

                // Prune solutions that aren't useful since they are bigger
                // than our target amount or that we have already solved
                if( (newTotal > amount) || solutions.containsKey(newTotal) )
                {
                    continue;
                }
              
                // This must me an optimal solution we haven't seen yet, so
                // add it to the list of solutions
                Node newNode = new Node( node, coin, newTotal, index );
                solutions.put( newTotal, newNode);
                
                // Stop now if we've found our answer!
                if( newTotal == amount )
                {
                    break;
                }
               
                // Otherwise add the new solution to the worklist and continue
                // finding solutions
                queue.add( newNode );
            }
        }

        // if we couldn't find a solution, return null
        // XXX: is there something better to return here?
        if( !solutions.containsKey( amount ) )
        {
            return null;
        }

        // Otherwise, walk the solutions tree to find our full solution
        List<Integer> results = expandSolution( solutions.get( amount ));
       
        // reverse the order of the results to be in descending value
        // like the list of coins we started with
        Collections.reverse( results );

        return results;
    }

    // helper method that extracts the full solution when given the leaf node`
    private static List<Integer> expandSolution( Node leaf )
    {
        List<Integer> results = new ArrayList<Integer>();
        for( Node node = leaf ; null != node.parent ; node = node.parent )
        {
            results.add( node.coin );
        }
        return results;
    }

    // internal data structure used by algorithm to store solutions
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
