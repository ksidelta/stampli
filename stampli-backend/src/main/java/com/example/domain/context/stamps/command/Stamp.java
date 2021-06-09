package com.example.domain.context.stamps.command;

import javax.persistence.*;

@Entity
@Table(name = "stamps")
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Embedded
    ClientBusinessId join;


    public static Stamp create() {
        return new Stamp();
    }

    public Integer getId() {
        return id;
    }
}

