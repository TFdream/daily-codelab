package com.bytebeats.codelab.interview.sort;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-19 22:53
 */
public class HeapSort {

    public void heap_sort(int array[]){
        int heapsize = array.length;
        buildHeap(array);

        for(int i=0;i<array.length-1;i++){
            // swap the first and the last
            swap(array, 0, heapsize-1);
            // heapify the array
            heapsize = heapsize - 1;
            heapify(array,0,heapsize);

        }
    }

    public void buildHeap(int array[]){
        int length = array.length;
        int heapsize = length;
        int nonleaf = length / 2 - 1;
        for(int i = nonleaf; i>=0;i--){
            heapify(array,i,heapsize);
        }
    }

    public void heapify(int array[], int i,int heapsize){
        int smallest = i;
        int left = 2*i+1;
        int right = 2*i+2;
        if(left<heapsize){
            if(array[i]>array[left]){
                smallest = left;
            }
            else smallest = i;
        }
        if(right<heapsize){
            if(array[smallest]>array[right]){
                smallest = right;
            }
        }
        if(smallest != i){
            swap(array, smallest, i);
            heapify(array,smallest,heapsize);
        }
    }

    protected void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]= temp;
    }
}
