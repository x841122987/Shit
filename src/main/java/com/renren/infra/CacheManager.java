package com.renren.infra;

public class CacheManager {

	private Cache[] caches;
	private int shardNumber;
	
	public CacheManager(int shardNumber) {
		this.shardNumber = shardNumber;
	}
	
	public void init() {
		caches = new Cache[shardNumber];
		for(int i = 0; i < shardNumber; ++ i) {
			caches[i] = new Cache();
		}
	}
	
	public void add(long key, long value) {
		int shard = (int) (key % shardNumber);
		caches[shard].add(key, value);
	}
	
	public int getShardNumber() {
		return shardNumber;
	}
	
	public String getCache(int shard) {
		return caches[shard].toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
