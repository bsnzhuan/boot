package com.example.demo;

public class ThreadTest {
    private int j ;

    public static void main(String[] args) {
        ThreadTest test = new ThreadTest();
        Inc inc = test.new Inc();
        Dec dec = test.new Dec();
        for (int i = 0; i < 2; i++) {
            Thread inct = new Thread(inc);
            Thread dect = new Thread(dec);
            inct.start();
            dect.start();
        }
    }

    class Dec implements Runnable{
        @Override
        public void run() {
           // for (int i = 0; i < 100; i++) {
                dec();;
            //}
        }
    }

    class Inc implements Runnable{
        @Override
        public void run() {
           // for (int i = 0; i < 100; i++) {
                inc();;
           // }
        }
    }

    private synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName()+"-dec:"+j);
    }

    private synchronized void inc(){
        j++;
        System.out.println(Thread.currentThread().getName()+"+inc:"+j);
    }
}
