package com.example.batchtest.demo.processor;

import com.example.batchtest.demo.bean.Transaction;
import com.example.batchtest.demo.bean.TransactionCsv;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class TransactionItemProcessor implements ItemProcessor<TransactionCsv, Transaction> {
    private Transaction transaction = new Transaction();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    @Override
    public Transaction process(TransactionCsv transactionCsv) throws  Exception{
        transaction.setDateTransaction(dateFormat.parse(transactionCsv.getDateTransaction()));
        transaction.setIdTransaction(transactionCsv.getIdTransaction());
        transaction.setMontant(transactionCsv.getMontant());
        return transaction;
    }
}
