package com.bytebeats.codelab.interview.sort;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-19 22:48
 */
public class MergeSort {

    public void mergeSort(int arr[], int start, int end) {
        if(start<end) {
            int mid = (start+end)/2;//数组重点
            mergeSort(arr,start,mid);//递归调用，排序前半段arr[start...mid]
            mergeSort(arr,mid+1,end);//递归调用，排序后半段arr[mid+1,end]
            mergeArray(arr, start, mid, end);//归并上述两段有序数组。
        }
    }

    private void mergeArray(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;// 左指针
        int j = mid + 1;// 右指针
        int k = 0;

        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = arr[j++];
        }

        // 把新数组中的数覆盖nums数组
        for (int k2 = 0; k2 < temp.length; k2++) {
            arr[k2 + low] = temp[k2];
        }
    }
}
