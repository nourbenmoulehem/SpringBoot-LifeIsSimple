package com.simply.simple_life.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Entity
@Data
@ToString
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private float amount;

    private String description;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne // one category can have many transactions
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Categories category;
}
