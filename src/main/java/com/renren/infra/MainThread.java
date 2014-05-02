package com.renren.infra;

import java.util.concurrent.CountDownLatch;

public class MainThread implements Runnable {

	private long start;
	private long end;
	private int threadNum;
	
	private CacheManager cache;
	
	private CountDownLatch finished;

	private Thread[] threads;
	
	private String prefix;
	private int shardNumber;
	private Thread fileThread;
	

	public MainThread(long start, long end, int threadNum, String prefix, int shardNumber) {
		this.start = start;
		this.end = end;
		this.threadNum = threadNum;
		this.prefix = prefix;
		this.shardNumber = shardNumber;
	}

	@Override
	public void run() {
		cache = new CacheManager(shardNumber);
		cache.init();
		
		finished = new CountDownLatch(threadNum);
		
		threads = new Thread[threadNum];
		long step = (end - start) / threadNum;
		for (int i = 0; i < threadNum; ++i) {
			threads[i] = new Thread(new CalThread(start + i * step, start
					+ (i + 1) * step, cache, finished));
		}

		fileThread = new Thread(new FileThread(prefix, cache));
		
		System.out.println("\n========================================");
		long st = System.currentTimeMillis();
		
		for(int i = 0; i < threadNum; ++ i) {
			threads[i].start();
		}	
		
		fileThread.start();	

		try {
			finished.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			fileThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("MainThrad [" + start + ", " + end + ") used " + (System.currentTimeMillis() - st) + "ms");
		System.out.println("========================================\n");
	}

	public static void main(String[] args) {

		Thread main = new Thread(new MainThread(5730000000L, 5740000000L, 4, "/tmp/shit_test", 10000));
		main.start();
		try {
			main.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
