package com.bytebeats.codelab.interview.sort;

/**
 * 基本思想：
 * 在要排序的一组数中，选出最小的一个数与第一个位置的数交换；然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。
 * @author Ricky
 *
 */
public class SelectSort extends Sort {

	@Override
	public void sort(int[] arr, int start, int end) {
		int len = end - start + 1;
		for (int i = 0; i < len; i++) {
			int index = i;
			for (int j = i + 1; j < len; j++) {
				if (arr[j] < arr[index])
					index = j;
			}
			if (index != i) {
				swap(arr, i, index);
			}
		}
	}

}
