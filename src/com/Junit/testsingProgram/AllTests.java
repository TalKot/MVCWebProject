package com.Junit.testsingProgram;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestingController.class, TestingModle.class })
public class AllTests {

}
