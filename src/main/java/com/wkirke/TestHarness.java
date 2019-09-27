package com.wkirke;

import com.wkirke.primeGenerators.PrimeNumberGenerator;
import com.wkirke.primeGenerators.SimpleGenerator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

public class TestHarness {
    private PrimeNumberGenerator generator = new SimpleGenerator();
    private Integer rangeStart = null;
    private Integer rangeEnd = null;
    private String errorMsg = null;
    private boolean initOk = false;

    /**
     * Very simple command line parsing
     * @param args Input arguments
     */
    public void parseCommandLine(String[] args) {
        try {
            for (String arg : args) {
                if (rangeStart == null) {
                    rangeStart = Integer.parseInt(arg);
                    continue;
                }
                if (rangeEnd == null) {
                    rangeEnd = Integer.parseInt(arg);
                    continue;
                }
                if (arg.equals("nullGenerator")) {
                    //Special test value to force an exception while running
                    generator = null;
                    continue;
                }
                errorMsg = "unrecognized parameter: " + arg;
                return;
            }
        } catch (Exception e) {
            errorMsg = "Caught exception: " + getStackTrace(e);
            return;
        }

        //Got required arguments?
        if (rangeEnd != null) {
            initOk = true;
        } else {
            errorMsg = "missing required parameter(s)";
        }
    }

    //Command line test harness
    public static void main(String[] args) {
        TestHarness instance = new TestHarness();

        //parse args
        instance.parseCommandLine(args);
        if (!instance.isInitOk()) {
            instance.showHelp();
            return;
        }

        //generate the output
        instance.run();
    }

    private void showHelp() {
        showHelp(null);
    }

    private void showHelp(String extraMsg) {
        System.out.println("\nUsage:");
        System.out.println(this.getClass().getSimpleName() + " startNum endNum");
        System.out.println("   startNum    Range starting number");
        System.out.println("   endNum      Range ending number");
        System.out.println();
        if (errorMsg != null) {
            System.out.println("ERROR: " + errorMsg);
        }
        if (extraMsg != null) {
            System.out.println("ERROR: " + extraMsg);
        }
    }


    private void run() {
        try {
            //DO THE WORK
            List<Integer> results = generator.generate(rangeStart, rangeEnd);
            System.out.println(Arrays.toString(results.toArray()));
        } catch (Exception e) {
            showHelp("Caught exception: " + getStackTrace(e));
        }
    }


    public Integer getRangeStart() {
        return rangeStart;
    }

    public Integer getRangeEnd() {
        return rangeEnd;
    }

    public boolean isInitOk() {
        return initOk;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
