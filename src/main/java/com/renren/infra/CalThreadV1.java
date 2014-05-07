package com.renren.infra;

import java.util.concurrent.CountDownLatch;

public class CalThreadV1 implements Runnable {

	private long start;
	private long end;
	private AnsSet set;
	private CountDownLatch finished;
	
	public CalThreadV1(long start, long end, AnsSet set, CountDownLatch finished) {
		this.start = start;
		this.end = end;
		this.set = set;
		this.finished = finished;
	}
	
	@Override
	public void run() {
		
		long st = System.currentTimeMillis();
		
		for (long x = start; x < end; ++x) {
			long key = Core.getNext(x);
			if(set.contains(key)) {
				set.add(x);
				System.out.println("=====[FIND " + x + "]=====");
			}
		}
		
		System.out.println("CalThrad [" + start + ", " + end + ") used " + (System.currentTimeMillis() - st) + "ms");
		finished.countDown();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
