package com.renren.infra;

import java.util.HashSet;

public class AnsSet {

	private HashSet<Long> set = new HashSet<Long>();
	private Object lock = new Object();
	
	public boolean contains(long key) {
		synchronized (lock) {
			return set.contains(key);
		}
	}
	
	public void add(long key) {
		synchronized (lock) {
			set.add(key);
		}
	}
	
	public int size() {
		synchronized (lock) {
			return set.size();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
