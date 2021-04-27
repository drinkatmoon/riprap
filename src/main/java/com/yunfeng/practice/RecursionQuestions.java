package com.yunfeng.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.编程实现斐波那契数列求值 f(n)=f(n-1)+f(n-2)
 * 2.编程实现求阶乘 n!
 * 3.编程实现一组数据集合的全排列
 * leetcode:
 * Climbing Stairs（爬楼梯） https://leetcode-cn.com/problems/climbing-stairs/
 *
 */
public class RecursionQuestions {
    Map<Integer,Integer> resMap = new HashMap<Integer,Integer>();
    /**
     * 爬楼梯问题：
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //采用递归方式,写出递推公式,第n阶楼梯的爬法为：f(n)=f(n-2)+f(n-1)
        //写出终止条件：n=1时，f(n)=1;n=2时，f(n)=2
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        int res_n=0;
        for(int cn : new int[]{n-2,n-1}){
            if(resMap.containsKey(cn) ){
                res_n += resMap.get(cn);
            }else{
                res_n += climbStairs(cn);
                resMap.put(cn,climbStairs(cn));
            }
        }
        return res_n;
    }
    public static void main(String[] args) {
        RecursionQuestions rq = new RecursionQuestions();
        int res = rq.climbStairs(5);
        System.out.println(res);
    }
}
