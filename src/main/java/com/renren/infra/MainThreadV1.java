package com.renren.infra;

import java.util.concurrent.CountDownLatch;

public class MainThreadV1 implements Runnable {

	private long start;
	private long end;
	private int threadNum;
	
	private AnsSet set;
	
	private Thread[] threads;
	
	private CountDownLatch finished;
	
	public MainThreadV1(long start, long end, int threadNum) {
		this.start = start;
		this.end = end;
		this.threadNum = threadNum;
	}

	public void init() {
		set = new AnsSet();
		set.add(Core.X);
	}
	
	public AnsSet getSet() {
		return set;
	}
	
	@Override
	public void run() {
	
		finished = new CountDownLatch(threadNum);
		
		threads = new Thread[threadNum];
		long step = (end - start) / threadNum;
		for (int i = 0; i < threadNum; ++i) {
			threads[i] = new Thread(new CalThreadV1(start + i * step, start
					+ (i + 1) * step, set, finished));
		}
		
		System.out.println("\n========================================");
		long st = System.currentTimeMillis();
		
		for(int i = 0; i < threadNum; ++ i) {
			threads[i].start();
		}	
		
		try {
			finished.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("MainThrad [" + start + ", " + end + ") used " + (System.currentTimeMillis() - st) + "ms");
		System.out.println("========================================\n");
	}
	
	public static void main(String[] args) {
		MainThreadV1 main = new MainThreadV1(Core.MIN, Core.MAX, 4);
		main.init();

		int size = main.getSet().size();
		do {
			main.run();
			int newSize = main.getSet().size();
			if(size == newSize) {
				break;
			}
			size = newSize;			
		}while(true);
	}

}
