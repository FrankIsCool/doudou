package com.common.redis;


public class RedisConfig {
    /**
     * 彩票类型
     */
    public static final String LOTTERYTYPE = "LotteryType";
    /**
     * 彩票
     */
    public static final String LOTTERY = "Lottery";

    public static String getLotteryKey(String lotteryId,String lotteryNo){
        String key = RedisConfig.LOTTERY+lotteryId+"&"+lotteryNo;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI+"A" + getLotteryKey(finalI+"A", finalI+"A"));
                }
            }.run();
            new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI+"B" + getLotteryKey(finalI+"B", finalI+"B"));
                }
            }.run();
            new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI+"C" + getLotteryKey(finalI+"C", finalI+"C"));
                }
            }.run();
        }
    }
}
