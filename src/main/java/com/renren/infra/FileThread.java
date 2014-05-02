package com.renren.infra;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileThread implements Runnable {

	private String prefix;
	private CacheManager cache;
	
	private int shardNumber;
	private String[] fileNames;
	
	public FileThread(String prefix, CacheManager cache) {
		this.prefix = prefix;
		this.cache = cache;
	}
	
	@Override
	public void run() {
		shardNumber = cache.getShardNumber();
		
		fileNames = new String[shardNumber];
		for(int i = 0; i < shardNumber; ++ i) {
			fileNames[i] = prefix + "/" + i + ".data";
		}
		
		do {
			long st = System.currentTimeMillis();
			
			boolean empty = true;
			for(int i = 0; i < shardNumber; ++ i) {
				String s = cache.getCache(i);
				if(!s.isEmpty()) {
					empty = false;
				}
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(fileNames[i], true));
					bw.append(s);
					bw.flush();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(empty) {
				break;
			}
			System.out.println("FileThread costs " + (System.currentTimeMillis() - st) + "ms");
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		} while(!Thread.interrupted());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
