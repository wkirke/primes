# primes
Prime number generator code challenge for UpDox

##Build and Execute
This project is intended for use with IntelliJ.
* Clone the project to your computer
* Tell IntelliJ to open the project via File >> Open.
* Run all tests via: 
  * right-click on src/main/java
  * select "Run 'All Tests' with Coverage"
* Set program values and run the test harness via:
   * Run >> Run... >> Edit configurations
   * Applications >> Main >> Program Arguments
     * Enter starting number and ending number
   * Click Run
   * See the console for output.

##Details
See these files:
* src/main/java/
  * com.wkirke.primeGenerators.PrimeNumberGenerator
    * The interface as provided in the code challenge
  * com.wkirke.primeGenerators.SimpleGenerator
    * Implementation class
  * com.wkirke.TestHarness
    * Command line program to exercise SimpleGenerator
* src/test/java
  * com.wkirke.primeGenerators.SimpleGeneratorTest
    * Fully tests the 2 methods in SimpleGenerator
  * com.wkirke.TestHarnessTest
    * Not sure if this was needed. Implemented it anyway.  Was fun figuring out how to capture stdout.

## Testing
Test Driven Development was used, but I didn't think to check in the iterations into GitHub.

I did leave some commented-out code in the isPrime() method, and added some comments to the test cases about how they 
failed with a partial implementation (that returned true for 13, and false for everything else).

## Author

* **Bill Kirke** - [Primes](https://github.com/wkirke/primes)
