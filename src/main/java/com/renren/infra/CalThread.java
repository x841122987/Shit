package com.renren.infra;

import java.util.concurrent.CountDownLatch;

public class CalThread implements Runnable {

	private long start;
	private long end;
	private CacheManager cache;
	private CountDownLatch finished;
	
	public CalThread(long start, long end, CacheManager cache,
			CountDownLatch finished) {
		this.start = start;
		this.end = end;
		this.cache = cache;
		this.finished = finished;	
	}

	@Override
	public void run() {
		
		long st = System.currentTimeMillis();
		
		for (long x = start; x < end; ++x) {
			long key = Core.getNext(x);
			cache.add(key, x);
		}
		
		System.out.println("CalThrad [" + start + ", " + end + ") used " + (System.currentTimeMillis() - st) + "ms");
		finished.countDown();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
