package com.jira.model.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sprint")
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column (name = "start_date")
    private LocalDateTime startDate;
    @Column (name = "end_date")
    private LocalDateTime endDate;
}
