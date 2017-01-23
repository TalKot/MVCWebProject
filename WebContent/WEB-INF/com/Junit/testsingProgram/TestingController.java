package com.Junit.testsingProgram;

import static org.junit.Assert.*;
import org.junit.Test;
import com.shenkar.controller.ProgramController;

public class TestingController  {
	ProgramController abc = new ProgramController();

	@Test
	public void test() {
		if (abc == null){
			fail("server couldn't be created!");
		}
	}

}
