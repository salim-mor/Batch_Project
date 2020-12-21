package com.example.batchtest.demo.bean;

import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionCsv {
    private long idTransaction;
    private long idCompte;
    private long montant;
    private String dateTransaction;
}
