package com.villageroad.web.test;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadABC extends Thread {
    private static AtomicInteger state = new AtomicInteger(0);
    private static String ABC = "ABC";
    private int type;

    ThreadABC(int type) {
        this.type = type;
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; )
            if (state.get() % 3 == type) {
                System.out.println(ABC.charAt(state.get() % 3));
                state.addAndGet(1);
                i++;
            }
    }

    public static void main(String[] args) {
        ThreadABC a = new ThreadABC(0);
        ThreadABC b = new ThreadABC(1);
        ThreadABC c = new ThreadABC(2);
        a.start();
        b.start();
        c.start();

    }
}
