package com.example.domain.context.stamps.command;

import javax.persistence.*;

@Entity
@Table(name = "stamps")
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "clientId")
    @JoinColumn(name = "businessId", referencedColumnName = "businessId")
    StampBusinessEntity join;

    public static Stamp create(StampBusinessEntity parent) {
        final var stamp = new Stamp();
        stamp.join = parent;
        return stamp;
    }

    public Integer getId() {
        return id;
    }
}

