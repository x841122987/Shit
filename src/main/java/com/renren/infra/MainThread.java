package com.renren.infra;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class MainThread implements Runnable {

	private long start;
	private long end;
	private int threadNum;
	
	private String prefix;
	private long fileStep;
	
	public HashMap<Long, StringBuffer> map;

	private Thread[] threads;
	private CountDownLatch finished;
	
	private FileThread fileThread;

	public MainThread(long start, long end, int threadNum, String prefix, long fileStep) {
		this.start = start;
		this.end = end;
		this.threadNum = threadNum;
		this.prefix = prefix;
		this.fileStep = fileStep;
	}

	@Override
	public void run() {
		System.out.println("\n========================================");
		long st = System.currentTimeMillis();
		
		map = new HashMap<Long, StringBuffer>();
		threads = new Thread[threadNum];
		finished = new CountDownLatch(threadNum);
		
		long step = (end - start) / threadNum;
		for (int i = 0; i < threadNum; ++i) {
			threads[i] = new Thread(new CalThread(start + i * step, start
					+ (i + 1) * step, map, finished, fileStep));
			threads[i].start();
		}

		try {
			finished.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		fileThread = new FileThread(prefix, map);
		fileThread.run();
		
		System.out.println("MainThrad [" + start + ", " + end + ") used " + (System.currentTimeMillis() - st) + "ms");
		System.out.println("========================================\n");
	}

	public static void main(String[] args) {

		MainThread main = new MainThread(5730000000L, 5740000000L, 1, "/tmp/shit_test", 1000000L);
		main.run();
	}

}
