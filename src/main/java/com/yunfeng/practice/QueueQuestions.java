package com.yunfeng.practice;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1.用数组实现一个顺序队列
 * 2.用链表实现一个链式队列
 * 3.实现一个循环队列
 * Leetcode：
 * Design Circular Deque（设计一个双端队列） https://leetcode-cn.com/problems/design-circular-deque/
 * Sliding Window Maximum（滑动窗口最大值） https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class QueueQuestions {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //使用java中的优先队列来动态获取最大值
        //但是在构建优先队列的过程，队首元素作为最大值不一定就是滑动窗口中的最大值，
        // 这种情况下这个值在数组nums中的位置出现在滑动窗口的左边界的左侧，因此在向右滑动窗口时，需要将其从队列中移除
        // 这里要借助元素所在的下标来判断最大值是不是滑动窗口中值
        int numsLen = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0]!=pair2[0]? pair2[0]-pair1[0]:pair2[1]-pair1[1];
            }
        });
        //初始化队列：去首个滑动窗口内的值入队
        for(int i=0;i<k;i++){
            pq.offer(new int[]{nums[i],i});
        }
        //定义一个
        // 结果数据，用于存放各个滑动窗口的最大值
        int[] ans = new int[numsLen-k+1];
        //首个窗口的最大值
        ans[0] = pq.peek()[0];
        for(int i=k;i<numsLen;++i){
            //讲遍历到的第i个元素入队
            pq.offer(new int[]{nums[i],i});
            //如果队首元素不在窗口区间内，则从队列中删除
            while(pq.peek()[1]<=i-k){
                pq.poll();
            }
            //取出第i个窗口的最大值放入数组
            ans[i-k+1] = pq.peek()[0];
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
