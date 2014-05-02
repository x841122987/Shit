package com.renren.infra;

public class Main {
	
	public static final long START = 0L;
	public static final long END = 1000000000L;
	public static final long TIMES = 1L;  // how many times we run maintThread
	public static final long STEP = (END - START) / TIMES;

	public static final String PREFIX = "/tmp/shit_test";
	public static final int SHARDNUM = 10000;
	
	public static void main(String[] args) {

		for(long x = START; x < END; x += STEP) {
			Thread main = new Thread(new MainThread(x, x + STEP, 1, PREFIX, SHARDNUM));
			main.start();
			try {
				main.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}
	}

}
