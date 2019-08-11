package com.batch.springbatch.listener;

import javax.batch.api.chunk.listener.ItemReadListener;

public class ReadListener implements ItemReadListener {
    @Override
    public void beforeRead() throws Exception {
//        System.out.println("Starting a Read Operation");
    }

    @Override
    public void afterRead(Object o) throws Exception {
//        System.out.println("Read Complete");
    }

    @Override
    public void onReadError(Exception e) throws Exception {
        System.out.println("Exception while reading");
    }
}
