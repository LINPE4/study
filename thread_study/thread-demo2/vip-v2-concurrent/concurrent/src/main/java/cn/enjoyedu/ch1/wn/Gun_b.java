package cn.enjoyedu.ch1.wn;

/**
 * 类说明：
 */
public class Gun_b {
    private Integer zd=0;
    public synchronized void put(){
        while(zd>=20) {
            try {
                System.out.println(Thread.currentThread().getName() + " 发现子弹已经装满了，当前子弹剩余=" + zd);
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        zd++;
        System.out.println(Thread.currentThread().getName() + " 装入子弹一枚，当前子弹剩余=" + zd);
        notifyAll();
    }

    public synchronized void get(){
        while (zd<=0) {
            try {
                System.out.println(Thread.currentThread().getName() + " 发现子弹已经射完了，当前子弹剩余=" + zd);
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        zd--;
        System.out.println(Thread.currentThread().getName() + " 发射一枚子弹，当前子弹剩余=" + zd);
        notifyAll();
    }
    static class Commer implements Runnable{
        Gun_b gun;
        public Commer(Gun_b gun){
            this.gun =gun;
        }
        @Override
        public void run() {
            while(true) {
                gun.get();
                try {
                    Thread.sleep(12);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
    static class Product implements Runnable{
        Gun_b gun;
        public Product(Gun_b gun){
            this.gun =gun;

        }
        @Override
        public void run() {
            while (true) {
                gun.put();
                try {
                    Thread.sleep(6);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Gun_b gun = new Gun_b();
        for(int i =0;i<6;i++) {
            new Thread(new Product(gun),"生产者"+i).start();
        }
        for(int i =0;i<6;i++) {
            new Thread(new Commer(gun),"消费者"+i).start();
        }
    }
}
