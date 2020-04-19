package com.example.demo;

import java.util.LinkedList;
import java.util.Queue;

public class CodeTest {
    /*public static void main(String[] args) {
        StringBuffer str = new StringBuffer("A BC");
        int P1 = str.length() - 1;
        for (int i = 0; i <= P1; i++)
            if (str.charAt(i) == ' ')
                str.append("  ");

        int P2 = str.length() - 1;
        while (P1 >= 0 && P2 > P1) {
            char c = str.charAt(P1--);
            if (c == ' ') {
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
            } else {
                str.setCharAt(P2--, c);
            }
        }
        System.out.println(str);
    }*/
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        while(queue.peek() != null){
            String str = queue.poll();
            if("a".equals(str)){

            }else{
                queue.offer(str);
            }
        }
        System.out.println(queue.element());

    }
}
