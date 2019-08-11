package com.batch.springbatch.listener;

import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkListener implements org.springframework.batch.core.ChunkListener {
    @Override
    public void beforeChunk(ChunkContext chunkContext) {

    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        System.out.println("Processed "+ chunkContext.getStepContext().getStepExecution().getCommitCount()+" chunks");

    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {

    }
}
