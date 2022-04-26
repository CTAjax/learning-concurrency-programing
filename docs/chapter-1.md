# Chapter 1 并发的优缺
## 1.什么是线程？
    一个进程的执行流

## 2.并发的好处
    让应用响应更快，更好的利用硬件资源

## 3.并发的风险
    * 线程饥饿
        出现的原因：
            当线程发生io阻塞时，后续的流程无法往下执行。
        解决方案：
            为每个线程任务设置超时时间
    
    * 死锁
        出现的原因：
            两个以上的线程争夺同一个资源
        解决方案：
            顺序执行或避免分享变量可变

    * 竞态条件
        出现的原因：
            两个以上线程在处理同一数据
        解决方案：
            避免让同一数据在不同线程中被操作

## 4.Java volatile 关键字
    * 下面代码执行结果是什么？
    public class RaceCondition {
    private static boolean done;

    public static void main(final String[] args) throws InterruptedException {
            new Thread(
                    new Runnable() {
                        public void run() {
                            int i = 0;
                            while (!done) {
                               i++;
                            }
                            System.out.println("Done!");
                        }
                    }).start();
            System.out.println("OS: " + System.getProperty("os.name"));
            Thread.sleep(2000);
            done = true;
            System.out.println("flag done set to true");
        }
    }
        在不同的java mode(client, server)下会产生不同的结果， 原因是因为在不同线程之间访问同一变量时， 在JIT的优化下，
    如果是server mode的话不会去读取内存的值而是直接读取了寄存器的值。如果要强制线程每次执行时都去访问内存， 需要加上volatile关键字。
    如果要让上面的程序按照"期望"的运行下去， 可以使用volatile关键字修饰变量， 或者使用同步方法 
    public static synchronized boolean getFlag() { return done; }
    public static synchronized void setFlag(boolean flag) { done = flag; } 
    来解决
    
    * 什么是JIT?
        它是一个强大的工具，可以努力优化代码以使其运行得更快。

## 5.什么是内存屏障?
    简单来说， 它会将本地或者是工作内存拷贝到主内存中

## 6.为什么要避免分享可变
    分享可变变量往往会让开发者忘记使用同步或其他方式来处理这些变量， 而这些错误在实际运行中又不会报错，让人
    摸不着头脑， 常常导致的结果是， 程序已经不是按照我们设想的那样执行了， 但是我们还是无法发现