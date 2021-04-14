package com.yunfeng.practice;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 关于数组与链表几个必知必会的代码实现
 * 数组：
 * 1.实现一个支持动态扩容的数组
 * 2.实现一个大小固定的有序数组，支持动态增删改操作
 * 3.实现两个有序数组合并为一个有序数组
 */
/*
    对应的leetcode练习题
    数组：
    Tree sum（求三数之和）：https://leetcode-cn.com/problems/3sum/
    Majority Element（求众数）：https://leetcode-cn.com/problems/majority-element/
    Missing Positive（求缺失的第一个正数）https://leetcode-cn.com/problems/first-missing-positive/
 */
public class ArrayQuestions {
    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
     * 请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        //定义一个数组
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        //特殊情况处理
        if(nums==null || nums.length<3){
            return  res;
        }
        //先对数组排序
        Arrays.sort(nums);
//        i表示每组计算首个元素的位置
        for(int i=0;i<nums.length;i++){
            //特判
            if(nums[i]>0 ){
                return  res;
            }
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            //在i后定义一个低位坐标L，在定位一个高位坐标R，L从左往右递增，R反之,L>=R时循环结束
            int L = i+1;
            int R = nums.length-1;
            while(L<R){
                //计算i，L，R的和，分三种情况处理
                if(nums[i]+nums[L]+nums[R]==0){
                    Integer[] arrays = new Integer[]{nums[i], nums[L], nums[R]};
                    res.add( Arrays.asList(arrays));
                    while(L<R && nums[L]==nums[L+1]){
                        L = L+1;
                    }
                    while(L<R && nums[R]==nums[R-1]){
                        R = R-1;
                    }
                    L++;
                    R--;
                }else if(nums[i]+nums[L]+nums[R]>0){
                    R--;
                }else{
                    L++;
                }
            }
        }
        return res;
    }

    /**
     *给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    public int majorityElement(int[] nums) {
        int res = -1 ;
        if(nums==null || nums.length==0){
            return res;
        }
        int n = nums.length;
        //对数组排序
        Arrays.sort(nums);
        //0，1，1，1
        for(int i=0;i<=nums.length/2;i++){
            if(nums[i]==nums[i+(nums.length/2)]){
                res=nums[i];
                break;
            }
        }
        return res;
    }

    /**
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 分析：未出现的正整数肯定在1 ~ N+1之中
     * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
     * 借助原地哈希思想，将原数组通过自定义哈希函数改造成hash表
     * hashId=f(num)=num-1
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        //先计算出数组长度N
        int len = nums.length;
        //便利数组，将下标位置与实际值不符合哈希函数的进行交换处理
        for(int i=0;i<len;i++){
            //只有当数组中元素的值>0且不超出数组长度范围时，才适用于哈希函数
            //这里使用while是因为交换后到这里的新值不一定是适合这个位置的，因此需要重新进行判断交换
            while(nums[i]>0 && nums[i]<=len && nums[nums[i]-1]!=nums[i]){
                //参数：原始数组，hash后位置,当前实际位置
                swap(nums,nums[i]-1,i);
            }
        }
        System.out.println( );
        //从0开始，有小到大，一旦发现处理后的hash表中存在idx与其值不符合hash函数的，如i，那么i+1就是查到的最小不存在的正数
        for(int i=0;i<len;i++){
            if(nums[i] != i+1){
                return i+1;
            }
        }
        //如果都一一匹配，则返回len+1
        return  len+1;
    }

    private void swap(int[] nums, int hash_inx, int cur_inx) {
        //把hash_idx的旧值先去取出来
        int tmp = nums[hash_inx];
        //将cur_inx的元素放到hash_idx位置
        nums[hash_inx] = nums[cur_inx];
        //将hash_idx上旧值放到cur_idx位置
        nums[cur_inx] = tmp;
    }

    public static void main(String[] args) {
        int i =0;
        int j =0;
        System.out.println(i++ +",j++:"+ ++j);
        System.out.println(String.format("i=%d,j=%d",i,j));
        int[] nums = {3,0,-2,-1,1,2};
//        [3,0,-2,-1,1,2]
//        int[] nums2= {};
        ArrayQuestions quest =  new ArrayQuestions();
//        List<List<Integer>> lists = quest.threeSum(nums);
//        System.out.println(lists);
//        int[] nums2 = {2,2,1,1,1,2,2};
//        int res = quest.majorityElement(nums2);
//        System.out.println(res);
        int[] num3 = {3,4,-1,1};
        int i1 = quest.firstMissingPositive(num3);
        System.out.println(i1);
    }
}
