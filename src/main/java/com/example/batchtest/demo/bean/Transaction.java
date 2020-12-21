package com.example.batchtest.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Transaction {
    @Id
    private long idTransaction ;
    private long montant ;
    private Date dateTransaction ;
    private Date dateDebit ;
    @ManyToOne
    private Compte compte;
}
