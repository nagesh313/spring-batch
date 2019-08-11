package com.batch.springbatch.listener;

import javax.batch.api.chunk.listener.ItemWriteListener;
import java.util.List;

public class WriteListener implements ItemWriteListener {
    @Override
    public void beforeWrite(List<Object> list) throws Exception {
//        System.out.println("Staring a write operation");
    }

    @Override
    public void afterWrite(List<Object> list) throws Exception {
//        System.out.println("Write complete");
    }

    @Override
    public void onWriteError(List<Object> list, Exception e) throws Exception {
        System.out.println("Write Error");
    }
}
