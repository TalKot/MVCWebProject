package com.shenkar.model;

/*basic exception that will be throw on our methods*/
@SuppressWarnings("serial")

public class userAndTaskException extends Exception {
		public userAndTaskException(String message){
	        super(message);
	    }
	}
