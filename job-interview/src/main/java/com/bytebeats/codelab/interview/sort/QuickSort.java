package com.bytebeats.codelab.interview.sort;

/**
 * 基本思想：
 * 选择一个基准元素(通常选择第一个元素或者最后一个元素),通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
 * 此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
 * @author Ricky
 *
 */
public class QuickSort extends Sort {

	public void quickSort(int[] arr, int start, int end){
		
		if(start<end){
			int index = partition(arr, start, end);
			
			quickSort(arr, start, index-1);
			
			quickSort(arr, index+1, end);
		}
		
	}
	
	/**
	 * 数组分区
	 * @param arr
	 * @param start
	 * @param end
	 * @return
	 */
	private int partition(int[] arr, int start, int end){
		
		if(start<0 || end<start || end>arr.length){
			throw new RuntimeException("start,end invalid");
		}
		
		int pivot = arr[end];	//选末尾当哨兵
		int index = start-1;
		for(int i=start;i<end;i++){
			
			if(arr[i]<pivot){
				index++;
				swap(arr, i, index);
			}
		}
		swap(arr, index+1, end);
		return index+1;
	}

	@Override
	public void sort(int[] arr) {
		quickSort(arr, 0, arr.length-1);
	}

	public static void main(String[] args) {
		
		int[] arr ={10,8,12,5,9,16,11};
		
		QuickSort sort = new QuickSort();
		sort.printArray(arr);
		sort.sort(arr);
		sort.printArray(arr);
		
	}
}
