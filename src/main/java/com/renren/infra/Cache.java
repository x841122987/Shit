package com.renren.infra;

import java.util.LinkedList;
import java.util.Queue;

public class Cache {

	public static final String REGEX = ":";
	
	private Queue<Pair> queue = new LinkedList<Pair>();
	private Object lock = new Object();
	
	public class Pair {
		private long key;
		private long value;
		
		public Pair(long key, long value) {
			this.key = key;
			this.value = value;
		}
		
		public long getKey() {
			return key;
		}
		
		public void setKey(long key) {
			this.key = key;
		}
		
		public long getValue() {
			return value;
		}
		
		public void setValue(long value) {
			this.value = value;
		}
	}
	
	public void add(long key, long value) {
		synchronized (lock) {
			queue.add(new Pair(key, value));
		}
	}
	
	public Pair poll() {
		synchronized (lock) {
			return queue.poll();
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		synchronized (lock) {
			while(queue.size() > 0) {
				Pair pair = queue.poll();
				sb.append(pair.getKey()).append(REGEX).append(pair.getValue());
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
