package com.github.tomdican.program.experiment;

import java.util.ArrayList;
import java.util.List;

public class ParamInstance implements Runnable{

    // 线程安全
    private volatile List<String> ss = new ArrayList<>();
    public static void main(String[] args) {
        ParamInstance paramInstance = new ParamInstance();

        List<String> lists = null;
        for (int i = 0; i <= 100; i++) {
            if (i == 0) {
                lists = new ArrayList<String>();
            }
            String s = String.valueOf(i);
            lists.add(s);
            if (i > 0 && i % 10 == 0) {
                new Thread(paramInstance).start();
                paramInstance.set(lists);

                lists = new ArrayList<String>();
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String str: ss) {
            System.out.println("setPrint: "+str);
         }
    }

    public void set(List<String> strs) {
        synchronized(this.ss) {
            this.ss = strs;
        }
    }
}
