package com.example.batchtest.demo.processor;

import com.example.batchtest.demo.bean.Transaction;
import com.example.batchtest.demo.doa.TransactionRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class TransactionItemWriter implements ItemWriter<Transaction> {
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public void write(List<? extends Transaction> list) throws Exception{
        transactionRepository.saveAll(list);
    }


}
