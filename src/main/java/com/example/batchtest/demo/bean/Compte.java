package com.example.batchtest.demo.bean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Compte {
    @Id
    private long idCompte ;
    private long solde ;

}
