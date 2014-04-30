package com.renren.infra;

public class Main {
	
	public static final long START = 0L;
	public static final long END = 1000000000L;
	public static final long TIMES = 100L;  // how many times we run maintThread
	public static final long STEP = (END - START) / TIMES;

	public static final String PREFIX = "/tmp/shit";
	public static final long FILESTEP = 1000000L;  // key number in each file
	
	public static void main(String[] args) {

		for(long x = START; x < END; x += STEP) {
			MainThread main = new MainThread(x, x + STEP, 1, PREFIX, FILESTEP);
			main.run();			
		}
	}

}
