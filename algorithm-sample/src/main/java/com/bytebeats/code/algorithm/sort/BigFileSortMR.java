package com.bytebeats.code.algorithm.sort;

import java.io.*;

/**
 * 有一个文本文件，文件大小4G，其中包含5亿个随机产生的正整数(int类型)(一行一个)，<br>
 * 现要对这个文件进行排序(升序or降序)，要求内存占用尽可能少。
 *
 * @author Ricky Fung
 * @date 2017-02-15 14:14
 */
public class BigFileSortMr {

    private File mapDir = new File("D:/sort/map");

    private String mapFilePrefix = "map";

    /**
     * 将一个大文件分隔成多个有序的小文件，每个文件大小为coreSize
     * @param file
     * @param coreSize
     * @return
     * @throws IOException
     */
    private File[] map(File file, int coreSize) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            int[] arr = new int[coreSize];
            int loop = 0, index = 0;
            String line = null;
            while ((line=br.readLine())!=null){
                if(line==null || line.length()==0){
                    continue;
                }
                arr[index] = Integer.parseInt(line);
                index++;
                if(index==coreSize){
                    //排序
                    sort(arr, 0, index, true);
                    //写出到小文件中
                    writeFile(new File(mapDir, String.format("%s_%s", mapFilePrefix, loop)), arr, index);
                    loop++;
                    index = 0;
                }
            }
            //尾数
            if(index>0){
                loop++;
                //排序
                sort(arr, 0, index, true);
                //写出到小文件中
                writeFile(new File(mapDir, String.format("%s_%s", mapFilePrefix, loop)), arr, index);
            }

            File[] files = new File[loop+1];
            for(int i=0;i<=loop;i++){
                files[i] = new File(mapDir, String.format("%s_%s", mapFilePrefix, loop));
            }
            return files;
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将n个有序的小文件 合并成1个有序的大文件
     * @param files
     * @param mergeNum
     * @return
     */
    private File reduce(File[] files, int mergeNum){

        return null;
    }

    private File mergeSortFile(File[] files) {

        return null;
    }

    private void sort(int[] arr, int start, int end, boolean asc){
        //冒泡排序
        for (int i = start; i < end; i++) {
            for(int j = start; j<end-i-1; j++){
                //这里-i主要是每遍历一次都把最大的i个数沉到最底下去了，没有必要再替换了
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    private void writeFile(File file, int[] arr, int index) throws IOException {
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            for(int i=0;i<index; i++){
                bw.write(String.valueOf(arr[i]));
                bw.newLine();
            }
            bw.flush();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        BigFileSortMr sort = new BigFileSortMr();
        try {
            File input = new File("input.txt");
            File[] files = sort.map(input, 50000);

            System.out.println("map files:"+files.length);

            File output = sort.reduce(files, 2);    //二路归并
            System.out.println("output:"+output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
