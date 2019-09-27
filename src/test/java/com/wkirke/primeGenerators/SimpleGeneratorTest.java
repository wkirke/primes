package com.wkirke.primeGenerators;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGeneratorTest {
    static SimpleGenerator instance;

    @BeforeAll
    static void setUp() {
        instance = new SimpleGenerator();
    }

    @AfterAll
    static void tearDown() {
        instance = null;
    }

    //First TDD test - worked
    @Test
    void isPrime_12() {
        boolean result = instance.isPrime(12);
        assertFalse(result, "12 should not be prime");
    }

    //First TDD test, initially failed
    @Test
    void isPrime_13() {
        boolean result = instance.isPrime(13);
        assertTrue(result, "13 is prime");
    }

    @Test
    void generate_12_13() {
        List<Integer> result = instance.generate(12, 13);
        assertFalse(result.contains(12), "result contains 12");
        assertTrue(result.contains(13), "result contains 13");
    }

    //Second TDD test - failed when class only returned true for 13.
    @Test
    void generate_1_3() {
        List<Integer> result = instance.generate(1, 3);
        assertFalse(result.contains(1), "result contains 1");
        assertTrue(result.contains(2), "result contains 2");
        assertTrue(result.contains(3), "result contains 3");
    }

    //TDD test after implementing divisor loop
    // Should return 7901, 7907, 7919
    @Test
    void generate_7900_7920() {
        List<Integer> result = instance.generate(7900, 7920);
        assertTrue(result.contains(7901), "result contains 7901");
        assertTrue(result.contains(7907), "result contains 7907");
        assertTrue(result.contains(7919), "result contains 7919");
        assertTrue(result.size() == 3, "3 results returned");
    }

    //Getting coverage up, and testing other cases
    @Test
    void isPrime_negative() {
        boolean result = instance.isPrime(-12);
        assertFalse(result, "negative numbers should not be prime according to the definition (greater than 1)");
    }

    @Test
    void isPrime_zero() {
        boolean result = instance.isPrime(0);
        assertFalse(result, "zero should not be prime according to the definition (greater than 1)");
    }

    @Test
    void isPrime_one() {
        boolean result = instance.isPrime(1);
        assertFalse(result, "one should not be prime according to the definition (greater than 1)");
    }
}