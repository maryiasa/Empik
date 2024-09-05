package com.empik.listeners;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Log4j2
public class TestNGListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        log.info("\n=======================================================\n" +
                "Test " + result.getMethod().getMethodName() +  ": STARTED\n" +
                "=======================================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        log.info("\n=======================================================\n" +
                "Test " + result.getMethod().getMethodName() +  ": SUCCEED\n" +
                "=======================================================");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        log.info("\n=======================================================\n" +
                "Test " + result.getMethod().getMethodName() +  ": FAILED\n" +
                "=======================================================");
    }
}
