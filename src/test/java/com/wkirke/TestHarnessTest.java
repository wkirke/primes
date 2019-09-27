package com.wkirke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TestHarnessTest {
    TestHarness instance;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setup() {
        instance = new TestHarness();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        instance = null;
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void parseCommandLine_good() {
        String[] args = {"1", "3"};
        instance.parseCommandLine(args);
        assertTrue(instance.getRangeStart() == 1, "first number is 1");
        assertTrue(instance.getRangeEnd() == 3, "last number is 3");
        assertTrue(instance.isInitOk(), "parsing worked");
    }

    @Test
    void parseCommandLine_unexpected_arg() {
        String[] args = {"1", "3", "4"};
        instance.parseCommandLine(args);
        assertFalse(instance.isInitOk(), "parsing failed");
        assertTrue(instance.getErrorMsg().contains("unrecognized"));
    }

    @Test
    void parseCommandLine_bad_arg() {
        String[] args = {"1", "abc"};
        instance.parseCommandLine(args);
        assertFalse(instance.isInitOk(), "parsing failed");
        assertTrue(instance.getErrorMsg().contains("exception"));
    }

    @Test
    void parseCommandLine_missing_arg() {
        String[] args = {"1"};
        instance.parseCommandLine(args);
        assertFalse(instance.isInitOk(), "parsing failed");
        assertTrue(instance.getErrorMsg().contains("missing"));
    }

    @Test
    void main_good() {
        String[] args = {"1", "3"};
        TestHarness.main(args);
        String output = outContent.toString();
        assertTrue(output.contains("[2, 3]"), "output contains \"2, 3\"");
    }

    @Test
    void main_extra_arg() {
        String[] args = {"1", "3", "4"};
        TestHarness.main(args);
        String output = outContent.toString();
        assertTrue(output.contains("unrecognized"), "output contains \"unrecognized\"");
    }


    @Test
    void main_special_arg() {
        String[] args = {"1", "3", "nullGenerator"};
        TestHarness.main(args);
        String output = outContent.toString();
        assertTrue(output.contains("NullPointerException"), "output contains \"NullPointerException\"");
    }


}