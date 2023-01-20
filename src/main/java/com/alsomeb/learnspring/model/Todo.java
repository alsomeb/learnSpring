package com.alsomeb.learnspring.model;


import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50) // == desc varchar(50) not null
    private String desc;

    @Column(nullable = false)
    final private LocalDate created;

    @Column(nullable = false)
    private LocalDate lastUpdate;


    public Todo() {
        created = LocalDate.now();
        lastUpdate = LocalDate.now();
    }

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
