package com.yunfeng.practice;

/**
 * 设计一个双端队列
 * 1、不用设计成动态数组，使用静态数组即可
 * 2、设计 head 和 tail 指针变量
 * 3、head == tail 成立的时候表示队列为空
 * 4、tail + 1 == head
 * 注意：数组大小是给定大小+1，因为rear指向的位置始终是空的
 * 2）末尾插入要先赋值再移动rear指针
 * 3）队满与对空的条件
 * 4）使用used变量来判断队列的空与满的状态
 */
public class MyCircularDeque {
    private int used = 0;
    private int front = 0;
    private int rear = 0;
    private int capacity = 0;
    private int[] a = null;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        capacity = k;
        a = new int[capacity];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(isFull()){
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        a[front] = value;
        used++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(isFull()){
            return false;
        }
        a[rear] = value;
        rear = (rear+1)%capacity;
        used++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(isEmpty()){
            return false;
        }
        int ret = a[front];
        front = (front+1)%capacity;
        used--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(isEmpty()){
            return false;
        }
        int ret = a[(rear-1+capacity)%capacity];
        rear = (rear-1+capacity)%capacity;
        used--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if(isEmpty()){
            return -1;
        }
        return a[front];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if(isEmpty()){
            return -1;
        }
        return a[(rear-1+capacity)%capacity];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return used == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return used == capacity;
    }
}
