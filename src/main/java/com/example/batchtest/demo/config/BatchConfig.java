package com.example.batchtest.demo.config;

import com.example.batchtest.demo.bean.Transaction;
import com.example.batchtest.demo.bean.TransactionCsv;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;



@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
    @Autowired private ItemReader<TransactionCsv> transactionItemReader;
    @Autowired private ItemWriter<Transaction> transactionItemWriter ;
    @Autowired private ItemProcessor<TransactionCsv,Transaction> itemProcessor ;

    @Bean
    public Job job(){
        Step step = stepBuilderFactory.get("ETL-Transaction-File-Load")
                .<TransactionCsv,Transaction>chunk(100)
                .reader(transactionItemReader)
                .writer(transactionItemWriter)
                .processor(itemProcessor)
                .build();
        return jobBuilderFactory.get("ETL-Load").start(step).build();
    }

    @Bean
    public FlatFileItemReader<TransactionCsv> fileItemReader(@Value("${input}") Resource resource){
        FlatFileItemReader<TransactionCsv> flatFileItemReader=new FlatFileItemReader<>();
        flatFileItemReader.setName("CSV reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLineMapper(lineMapper());
        return flatFileItemReader;
    }
    @Bean
    public LineMapper<TransactionCsv> lineMapper() {
        DefaultLineMapper<TransactionCsv> defaultLineMapper=new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"idTransaction","idCompte","montant","dateTransaction"});
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper<TransactionCsv> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(TransactionCsv.class);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return defaultLineMapper;
    }
}
