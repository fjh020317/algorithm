package com.fjh.monotonousStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MonotonousStack {
    public static int[][] getLessNearNoRepeat(int[]arr){
        Stack<Integer>stack = new Stack<>();
        int[][]ret = new int[arr.length][2];
        for(int i = 0; i < arr.length ; i++){
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int pol = stack.pop();
                int rightIndex = i;
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                ret[pol][0] = leftIndex;
                ret[pol][1] = rightIndex;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int pol = stack.pop();
            int rightIndex = -1;
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            ret[pol][0]= leftIndex;
            ret[pol][1] = rightIndex;
        }
        return  ret;
    }

    public static int[][] getLessNear(int[]arr){
        if(arr == null){
            return  null;
        }
        int[][]ret = new int[arr.length][2];
        Stack<List<Integer>>stack = new Stack<>();
        for(int i = 0 ;  i < arr.length ;i++){
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                List<Integer> pol = stack.pop();
                int rightIndex = i;
                //左边最近的是,要求元素的下面的链表的最后一个
                int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1) ;
                for (int pollI : pol){
                    ret[pollI][0] = leftIndex;
                    ret[pollI][1] = rightIndex;
                }
            }
            if(!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                stack.peek().add(i);
            }else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()){
            List<Integer>pol = stack.pop();
            int rightIndex = -1 ;
            int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int pollI:pol){
                ret[pollI][0] = leftIndex;
                ret[pollI][1] = rightIndex;
            }
        }
        return  ret;
    }













    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getLessNearNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getLessNear(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
