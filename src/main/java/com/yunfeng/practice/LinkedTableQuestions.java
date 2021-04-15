package com.yunfeng.practice;

import com.sun.jmx.snmp.SnmpStatusException;

import java.util.PriorityQueue;

/**
 * 关于数组与链表几个必知必会的代码实现
 * 链表:
 * 1.实现单链表、循环链表、双向链表、支持增删操作
 * 2.实现单链表反转
 * 3.实现两个有序链表合并为一个有序链表
 * 4.实现求链表的中间节点
 */
/*
    对应的leetcode练习题
    链表：
    Linked List Cycle I（环形链表） https://leetcode-cn.com/problems/linked-list-cycle/
    Merge k Sorted Lists（合并 k 个排序链表） https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class LinkedTableQuestions {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode head2 = new ListNode(0);
        ListNode head3 = new ListNode(0);
        ListNode tail = head;
        ListNode tail2 = head2;
        ListNode tail3 = head3;
        int[] nums1 = {1,4,5};
        int[] nums2 = {1,3,4};
        int[] nums3 = {6,7};
        for(int i : nums1){
            tail.next = new ListNode( i);
            tail = tail.next;
        }
        for(int j : nums2){
            tail2.next = new ListNode( j);
            tail2 = tail2.next;
        }
        for(int k : nums3){
            tail3.next = new ListNode( k);
            tail3 = tail3.next;
        }
        System.out.println(head.next );
        System.out.println(head2.next );
        System.out.println(head3.next );
//        ListNode resNode= new LinkedTableQuestions().mergeTwoLists(head.next,head2.next);
        ListNode[] lists = {head.next ,head2.next ,head3.next };
        ListNode resNode= new LinkedTableQuestions().mergeKLists_3(lists);
        System.out.println(resNode);
    }

    /**
     * 多个链表合并方式1
     * 依次顺序合并，先合并第0与第1个，在将之前的结果与第二个链表合并，依次进行
     * @param lists
     * @return
     */
    public ListNode mergeKLists_1(ListNode[] lists) {
        ListNode ans = null;
        for(ListNode node:lists){
            ans = this.mergeTwoLists(ans,node);
        }
        return ans;
    }

    /**
     * 多个链表合并方式2
     * 使用递归方式实现两两合并
     * 递推公式：
     * merge(lists,int l,int r)=mergeTwoLinks(merge(lists, l,mid),merge(lists,mid,r))
     * @param lists
     * @return
     */
    public ListNode mergeKLists_2(ListNode[] lists) {
        return merge(lists,0, lists.length-1);
    }

    public ListNode merge(ListNode[] lists,int l,int r) {
        if(l==r){
            return lists[l];
        }
        if(l>r){
            return null;
        }
        int mid = (l+r)>>1;
        return mergeTwoLists(merge(lists,l,mid),merge(lists,mid+1,r));
    }
    /*
      在合并多个链表之前，现尝试合并两个链表
      三个变量，一个链表对象：作为结果链表，这样可以满足O(1)空间复杂度
      一个 apr指向a链表，一个bpr只想b链表，一个tail指针表示结果链表的尾指针
     */
    public ListNode mergeTwoLists(ListNode  a,ListNode b) {
        ListNode node = new ListNode();
        //特殊情况处理
        if(a==null || b==null){
            return a!=null?a:b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head,apr=a,bpr=b;
        //如果apr与bpr节点不为null，表示两个链表还没结束
        while(apr !=null && bpr != null){
            //谁小把谁放取出来
            if(apr.val<bpr.val){
                tail.next=apr;
                apr = apr.next;
            }else{
                tail.next =bpr;
                bpr = bpr.next;
            }
            //变更尾节点
            tail = tail.next;
        }
        //如果apr或bpr有一个链表遍历结束，则将另一个直接追加到结果链表中
        tail.next = (apr !=null ? apr : bpr);

        return head.next;
    }

    /**
     * 合并多个链表方式3
     * 使用优先队列：java的PriorityQueue的时间复杂度为logn
     * 队列里放各个链表的head节点，并按优先级存放读取
     * @param lists
     * @return
     */
    PriorityQueue<Status> queue = new PriorityQueue<Status>();
    public ListNode mergeKLists_3(ListNode[] lists) {
         for(ListNode node :lists){
             if(node != null){
                 queue.offer(new Status(node.val,node));
             }
         }
         //定义一个结果链表
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(!queue.isEmpty()){
            Status f = queue.poll();
            tail.next=f.ptr;
            tail = tail.next;
            if(f.ptr.next!=null){
                queue.offer(new Status(f.ptr.next.val,f.ptr.next));
            }
        }
        return head.next;
    }
    class Status implements Comparable<Status>{
        int val ;
        ListNode ptr;

        public Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status s2) {
            return this.val - s2.val ;
        }
    }
}
