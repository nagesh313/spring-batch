package com.batch.springbatch.config;

import com.batch.springbatch.entity.Score;
import com.batch.springbatch.entity.ScoreRaw;
import com.batch.springbatch.listener.ChunkListener;
import com.batch.springbatch.listener.ProcessorListener;
import com.batch.springbatch.listener.ReadListener;
import com.batch.springbatch.listener.WriteListener;
import com.batch.springbatch.proccessor.ScoreItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.persistence.EntityManagerFactory;

@Configuration
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    EntityManagerFactory emf;

    @Value("classpath:${resource-path}/**/*.csv")
    private Resource[] inputResources;

    @Bean
    public FlatFileItemReader<ScoreRaw> flatFileReader() {
        return new FlatFileItemReaderBuilder<ScoreRaw>().name("flatFileReader")
                .linesToSkip(1)
                .delimited()
                .names(new String[]{"round", "date", "team1", "ft", "ht", "team2"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ScoreRaw>() {{
                    setTargetType(ScoreRaw.class);
                }})
                .build();
    }

    @Bean
    public MultiResourceItemReader<ScoreRaw> reader() {
        return new MultiResourceItemReaderBuilder<ScoreRaw>()
                .name("scoreItemReader")
                .resources(inputResources)
                .delegate(flatFileReader())
                .build();
    }

    @Bean
    public ScoreItemProcessor processor() {
        return new ScoreItemProcessor();
    }

    @Bean
    public JpaItemWriter writer() {
        return new JpaItemWriterBuilder<>().entityManagerFactory(emf).build();
    }

    @Bean
    public Job importScoresJob(Step importScoresStep) {
        return jobBuilderFactory.get("importScoreJob")
                .incrementer(new RunIdIncrementer())
                .start(importScoresStep)
                .build();
    }

    @Bean
    public Step importScoresStep() {
        return stepBuilderFactory.get("importScoresStep")
                .<ScoreRaw, Score>chunk(10000)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(new ReadListener())
                .listener(new ProcessorListener())
                .listener(new WriteListener())
                .listener(new ChunkListener())
                .build();
    }
}
