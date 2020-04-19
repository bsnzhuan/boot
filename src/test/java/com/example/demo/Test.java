package com.example.demo;

public class Test{
    private static boolean flag = true;

    public synchronized static void run(){
        System.out.println(Thread.currentThread().getName()+"获取值before"+flag);
        flag = false;
        System.out.println(Thread.currentThread().getName()+"获取值after"+flag);
    }

    public static void main(String[] args) {
        /*ThreadA test = new ThreadA();
        test.service1();*/
        /*ThreadB test = new ThreadB();
        test.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.setFlag(false);
        System.out.println("已经赋值为false");
        System.err.print("syso err");*/
        ThreadC[] test = new ThreadC[100];
        for (int i = 0; i < 100; i++) {
            test[i] = new ThreadC();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            test[i].start();
        }
    }

}
    //重入锁，一个线程获取到锁没有释放是可以再获取锁的
    class ThreadA extends Thread{
        public synchronized void service1(){
            System.out.println("synchronized service1方法");
            service2();
        }

        public synchronized void service2(){
            System.out.println("synchronized service2方法");
            service3();
        }

        public synchronized void service3(){
            System.out.println("synchronized service3方法");
        }
    }
    //volatile声明变量可见性
    class ThreadB extends Thread{
        private volatile boolean flag = true;

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run() {
            System.out.println("进入run");
            while(flag){
                System.out.println("run");
            }
            System.out.println("停止线程");
        }
    }
    //操作volatile变量修改
    class ThreadC extends  Thread{
        volatile public static int count;

        public static int getCount() {
            return count;
        }

        public static void setCount(int count) {
            ThreadC.count = count;
        }

        private synchronized static void addCount() {
            for (int i = 0; i < 100; i++) {
                count++;
            }
            System.out.println(Thread.currentThread().getName()+":count=" + count);
        }

        @Override
        public void run() {
            addCount();
            //operation.addNum();
        }
    }
    class operation{
        public static int num;

        public synchronized static void addNum(){
            for (int i = 0; i < 100; i++) {
                num++;
            }
            System.out.println(Thread.currentThread().getName()+":num="+num);
        }
    }
