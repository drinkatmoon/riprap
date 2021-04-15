package com.yunfeng.practice;

import java.util.PriorityQueue;

public class ListNode {
    int val;
    ListNode next;
    public ListNode(){ }
    public ListNode(int val) {
        this.val = val;
    }
    public ListNode(int val,ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    }
}
