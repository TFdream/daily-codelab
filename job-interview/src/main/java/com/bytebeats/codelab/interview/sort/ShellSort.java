package com.bytebeats.codelab.interview.sort;

/**
 * ${DESCRIPTION}
 *
 * @author Ricky Fung
 * @create 2017-04-19 22:53
 */
public class ShellSort {

    public static void sort(int[] data) {
        int j = 0;
        int temp = 0;
        //每次将步长缩短为原来的一半
        for (int increment = data.length / 2; increment > 0; increment /= 2) {
            for (int i = increment; i < data.length; i++) {
                temp = data[i];
                for (j = i; j >= increment; j -= increment) {
                    if(temp > data[j - increment]) {//如想从小到大排只需修改这里
                        data[j] = data[j - increment];
                    } else {
                        break;
                    }
                }
                data[j] = temp;
            }
        }
    }
}
