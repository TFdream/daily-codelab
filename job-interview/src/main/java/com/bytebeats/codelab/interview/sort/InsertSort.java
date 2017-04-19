package com.bytebeats.codelab.interview.sort;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-19 22:25
 */
public class InsertSort {

    public void insert_sort(int arr[]) {
        int len = arr.length;
        for (int i = 1; i < len; i ++) {
            int j = i - 1;
            int k = arr[i];
            while (j > -1 && k < arr[j] ) {
                arr[j + 1] = arr[j];
                j --;
            }
            arr[j + 1] = k;
        }
    }
}
