package com.fzk.core;

import java.util.Comparator;

/**
 * order值比较器
 *
 * @see Ordered
 * <p>
 * author:fanzhoukai
 * 2019/4/27 23:13
 */
public class OrderComparator implements Comparator {
	/**
	 * 按照Ordered接口的返回值进行排序
	 */
	public int compare(Object o1, Object o2) {
		// 若入参为空，instanceof会返回false
		int i1 = o1 instanceof Ordered ? ((Ordered) o1).getOrder() : Integer.MAX_VALUE;
		int i2 = o2 instanceof Ordered ? ((Ordered) o2).getOrder() : Integer.MAX_VALUE;

		if (i1 > i2)
			return 1;
		if (i1 < i2)
			return -1;
		return 0;
	}
}
