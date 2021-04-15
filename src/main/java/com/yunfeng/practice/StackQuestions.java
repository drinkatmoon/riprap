package com.yunfeng.practice;

import java.util.*;

/**
 * 关于栈必会的代码：
 * 1.用数组实现一个顺序栈
 * 2.用链表实现一个链式栈
 * 3.编程模拟一个浏览器的前进，后退功能
 * LC练习题：
 * Valid Parentheses（有效的括号） https://leetcode-cn.com/problems/valid-parentheses/
 * Longest Valid Parentheses（最长有效的括号） https://leetcode-cn.com/problems/longest-valid-parentheses/
 * Evaluate Reverse Polish Notatio（逆波兰表达式求值） https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */


public class StackQuestions {
    /**
     * 借助hash表存放左右括号的key-value对
     * 借助栈遍历字符川的字符，若是左括号则入栈，否则出栈比对
     * @param s
     * @return
     */
    public boolean isValid(String s) {

        LinkedList<Character> stack = new LinkedList<Character>(){{add('?');}};
        if(s.length()>0 && s.charAt(0)!='{'  && s.charAt(0)!='['  && s.charAt(0)!='<'  && s.charAt(0)!='('  && s.charAt(0)!='?' ){
            return false;
        }
        char[] chars = s.toCharArray();
        for(char c: chars){
            if(c=='{' || c=='[' ||c=='(' ||c=='?' || c =='<'){
                stack.addLast(c);
            }else{
                char pref = stack.removeLast();
                if ((pref=='{' && c!='}')
                        ||(pref=='[' && c!=']') || (pref=='?' && c!='?')
                        ||(pref=='<' && c!='>')||(pref=='(' && c!=')') ){
                    return false;
                }
            }
        }
        return stack.size()==1;
    }

    public static void main(String[] args) {
//        boolean valid = new StackQuestions().isValid("{}[");
//        System.out.println(valid);
        int  res = new StackQuestions().longestValidParentheses2(")()())");
        System.out.println(res);

    }

    public int longestValidParentheses(String s) {
        //特判
        if(s==null || s.length()==0) return 0;
        //使用数组记录符合规范的字符下标
        int[] indexs = new int[]{};
        //使用stack判断字符是否符合规范
        LinkedList<Integer> stack = new LinkedList<Integer>();
//        stack.offer(-1);
        System.out.println(stack);
        List<Integer> res =new ArrayList();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)== '('  ){
                stack.offer(i);
            }
            if(!stack.isEmpty() &&   s.charAt(i) == ')'){
                int pref = stack.poll();
                if(s.charAt(pref)=='('){
                    res.add(pref);
                    res.add(i);
                }
            }

        }
        Collections.sort(res);
        System.out.println(res);
        /*
            从数组中找连续下标的最大长度
         */
        int ans =0;//结果值
        int n =  res.size();
        for(int i = 0;i<n; ){
            int j = i;
            while(j<n-1 && res.get(j+1) == (res.get(j)+1)){
                j++;
            }
            ans = Math.max(ans,j-i+1);
            i = j+1;
        }
        return ans;
    }
    public int longestValidParentheses2(String s) {
        int maxans = 0 ;
        if (s == null || s.length() == 0) return 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {//如果stack被清空了，需要重新压栈
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek()); //peek表示检索栈的头部元素
                }
            }
        }
        return maxans;
    }

}
