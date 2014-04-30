package com.renren.infra;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class FileThread implements Runnable {

	private String prefix;
	private HashMap<Long, StringBuffer> map;
	
	public FileThread(String prefix, HashMap<Long, StringBuffer> map) {
		this.prefix = prefix;
		this.map = map;
	}
	
	@Override
	public void run() {
		
		long st = System.currentTimeMillis();
		
		Iterator<Entry<Long, StringBuffer>> it = map.entrySet().iterator();
		while(it.hasNext()) {
			Entry<Long, StringBuffer> entry = it.next();
			long key = entry.getKey();
			StringBuffer value = entry.getValue();
			
			try {
				String file = prefix + "/" + key + ".data";
				BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
				
				writer.append(value);
				
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("FileThrad used " + (System.currentTimeMillis() - st) + "ms");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
