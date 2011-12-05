Java Implementation of [Ruby Quiz #154](http://rubyquiz.com/quiz154.html)
-------------------------------------

This project provides a solution to Ruby Quiz #154, which asks for a method to make change. This method should take an amount of change and a set of coins as input and provide the optimal solution, with optimal being the least number of coins used to total the desired amount.

The solution provided uses dynamic programming to quickly find the optimal solution. This algorithm is most similar to the last example solution on the Ruby quiz page, although it uses a different underlying data structure. This is a bottom-up approach that aggressively prunes non-optimal paths.  Since we know the solution for a zero amount in any coin system is no coins, we use that as a root and build larger optimal solutions for this coin system by adding  coins to create a n-ary tree of solutions, where n is the number denominations in the coin system. The algorithm avoids pursuing paths that generate less than optimal solutions and prunes paths that produce solutions larger than our desired amount.

There are a variety of test cases included in the project, mostly from the RSpec portion of [Yoan Blanc's Solution](http://blade.nagaokaut.ac.jp/cgi-bin/scat.rb/ruby/ruby-talk/288799). These tests provide many pathological coin systems that cause problems for naive algorithms and that were extremely useful during the process of developing this solution.

The entire commit history of this project is available in [my public github repository for this project](https://github.com/sandersch/Making-Change)

This project was generated and built with Apache Maven.

Charlie Sanders    
sanderscharlie@gmail.com
