package com.bytebeats.codelab.interview.sort;

/**
 * 基本思想：
 * 在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
 * @author Ricky
 *
 */
public class BubbleSort extends Sort {

	@Override
	public void sort(int[] arr){
		
		for(int i=0;i<arr.length;i++){
			
			for(int j=i+1;j<arr.length;j++){
				
				if(arr[i]>arr[j]){
					swap(arr, i, j);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		int[] arr = {13,27,49,78,34,12,64,1,8};
		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.printArray(arr);
		bubbleSort.sort(arr);
		bubbleSort.printArray(arr);
	}
}
