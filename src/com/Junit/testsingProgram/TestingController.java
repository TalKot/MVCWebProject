package com.Junit.testsingProgram;

import static org.junit.Assert.*;
import org.junit.Test;
import com.shenkar.controller.programController;

public class testingController  {
	programController abc = new programController();

	@Test
	public void test() {
		if (abc == null){
			fail("server couldn't be created!");
		}
	}

}
