package com.batch.springbatch.listener;

import org.springframework.batch.core.ItemProcessListener;

public class ProcessorListener implements ItemProcessListener {
    @Override
    public void beforeProcess(Object o) {
//        System.out.println("Staring a process operation");
    }

    @Override
    public void afterProcess(Object o, Object o2) {
//        System.out.println("Process operation completed");
    }

    @Override
    public void onProcessError(Object o, Exception e) {
        System.out.println("Process operation Error");
    }
}
