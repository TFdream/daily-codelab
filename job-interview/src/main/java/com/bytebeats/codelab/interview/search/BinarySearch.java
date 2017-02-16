package com.bytebeats.codelab.interview.search;

public class BinarySearch {

	public int search(int[] arr, int key){
		
		return binarySearch(arr, 0, arr.length, key);
	}
	
	protected int binarySearch(int[] arr, int low ,int high, int key){
		
		while(low<=high){
			
			int mid = (low + high) / 2;
			if (arr[mid]==key){
				return mid;
			}else if (arr[mid] < key){
				low = mid + 1;
			}else{
				high = mid - 1;
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		int[] arr = {1,3,5,9,10,15,20};
		
		System.out.println(new BinarySearch().search(arr, 9));
	}

}
