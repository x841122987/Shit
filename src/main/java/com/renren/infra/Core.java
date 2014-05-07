package com.renren.infra;

public class Core {

	public static final long NUM = 597;
	public static final long X = 6065038420L;
	public static final long MIN = 9612809L;
	public static final long MAX = 9995371004L;

	public static long getNext(long x) {
		int y = (int) (x / 1000000000L) + 1;
		do {
			int z = (int) (x / 100000000L) % 10;
			switch (z + 3) {
			case 3:
				x = case3(x);
			case 4:
				x = case4(x);
			case 5:
				x = case5(x);
			case 6:
				x = case6(x);
			case 7:
				x = case7(x);
			case 8:
				x = case8(x);
			case 9:
				x = case9(x);
			case 10:
				x = case10(x);
			case 11:
				x = case11(x);
			case 12:
				x = case12(x);
			default:
				break;
			}
			--y;
		} while (y > 0);
		return x;
	}

	private static long case3(long x) {
		long m = 5000000000L;
		if (x < m) {
			x += m;
		}
		return x;
	}

	private static long case4(long x) {
		long m = 100000L;
		long x1 = x / m;
		long x2 = x % m;
		long y1 = x1 * x1, y2 = 2 * x1 * x2, y3 = x2 * x2;
		y2 += y3 / m;
		y1 += y2 / m;
		return (y1 % m) * m + (y2 % m);
	}

	private static long case5(long x) {
		long m = 100000L;
		long x1 = x / m;
		long x2 = x % m;
		long y1 = 1001001001L / m;
		long y2 = 1001001001L % m;
		long z1 = x1 * y1, z2 = x1 * y2 + x2 * y1, z3 = x2 * y2;
		z2 += z3 / m;
		z1 += z2 / m;
		return (z2 % m) * m + (z3 % m);
	}

	private static long case6(long x) {
		if (x < 100000000L) {
			x += 9814055677L;
		} else {
			x = 10000000000L - x;
		}
		return x;
	}

	private static long case7(long x) {
		long m = 100000L;
		return m * (x % m) + x / m;
	}

	private static long case8(long x) {
		return case5(x);
	}

	private static long case9(long x) {
		long cx = 0L;
		long t = 1;
		while (x > 0) {
			int u = (int) (x % 10L);
			if (u > 0) {
				--u;
			}
			cx += t * u;
			t *= 10L;
			x /= 10L;
		}
		return cx;
	}

	private static long case10(long x) {
		if (x < 100000L) {
			x = x * x + 99999L;
		} else {
			x = x - 99999L;
		}
		return x;
	}

	private static long case11(long x) {
		while (x < 1000000000L) {
			x *= 10L;
		}
		return x;
	}

	private static long case12(long x) {
		long m = 100000L;
		long x1 = x / m;
		long x2 = x % m;
		long y1 = (x - 1L) / m;
		long y2 = (x - 1L) % m;
		long z1 = x1 * y1, z2 = x1 * y2 + x2 * y1, z3 = x2 * y2;
		z2 += z3 / m;
		z1 += z2 / m;
		return (z1 % m) * m + (z2 % m);
	}

	public static boolean check(long x) {
		do {
			x = getNext(x);
		} while(x != X);
		
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(getNext(X) == X);
		
//		long st = System.currentTimeMillis();
//
//		for (long x = 0L; x < 100000000L; ++x) {
//			getNext(x);
//		}
//
//		System.out.println((System.currentTimeMillis() - st) + "ms");
	}

}
