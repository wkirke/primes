package com.wkirke.primeGenerators;

import com.sun.org.apache.bcel.internal.util.ClassStack;

import java.util.ArrayList;
import java.util.List;

public class SimpleGenerator implements PrimeNumberGenerator {
    @Override
    public List<Integer> generate(int startingValue, int endingValue) {
        List<Integer> result = new ArrayList<>();
        //Handle args in either order
        int first = Math.min(startingValue, endingValue);
        int last = Math.max(startingValue, endingValue);
        //Test every value in the range
        for (int testVal = first; testVal <= last; ++testVal) {
            if (isPrime(testVal)) {
                result.add(testVal);
            }
        }
        return result;
    }

    @Override
    public boolean isPrime(int value) {
        if (value <= 1) {
            return false;
        }
        //inefficient, but gets the job done
        for (int divisor = 2; divisor < value; ++divisor) {
            if (value % divisor == 0) {
                return false;
            }
        }
        return true;

        //First TDD iplementation
//        if (value == 13) {
//            return true;
//        }
//        return false;
    }
}
