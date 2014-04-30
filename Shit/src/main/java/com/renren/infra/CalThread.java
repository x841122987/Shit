package com.renren.infra;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class CalThread implements Runnable {

	private long start;
	private long end;
	private HashMap<Long, StringBuffer> map;
	private CountDownLatch finished;
	
	private long fileStep;

	public CalThread(long start, long end, HashMap<Long, StringBuffer> map,
			CountDownLatch finished, long fileStep) {
		this.start = start;
		this.end = end;
		this.map = map;
		this.finished = finished;
		this.fileStep = fileStep;
	}

	@Override
	public void run() {
		
		long st = System.currentTimeMillis();
		
		for (long x = start; x < end; ++x) {
			long key = Core.getNext(x);
			put(key, x);
		}
		
		System.out.println("CalThrad [" + start + ", " + end + ") used " + (System.currentTimeMillis() - st) + "ms");
		finished.countDown();
	}

	private void put(Long key, Long value) {
		StringBuffer buffer;
		long file = key / fileStep;
		if (map.containsKey(file)) {
			buffer = map.get(file);
		} else {
			buffer = new StringBuffer("");
			map.put(file, buffer);
		}
		buffer.append(key + ":" + value + "\n");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
