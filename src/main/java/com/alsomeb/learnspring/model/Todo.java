package com.alsomeb.learnspring.model;


import javax.persistence.*;

import java.time.LocalDate;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50) // == desc varchar(50) not null
    private String desc;

    // AUTOGENERATE I CONSTRUCTOR
    private LocalDate created;

    private LocalDate lastUpdate;


    // No Args constructor behöver LocalDate för att kunna populera props med datum
    public Todo() {
        created = LocalDate.now();
        lastUpdate = LocalDate.now();
    }

    // Seeding av data måste också ha Datumhantering i denna constructor
    public Todo(String desc) {
        this.desc = desc;
        created = LocalDate.now();
        lastUpdate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    //Spring kanske behöver
    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDate getCreated() {
        return created;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", created=" + created +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
