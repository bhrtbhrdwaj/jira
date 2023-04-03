package com.jira.model.entity;

import com.jira.constant.TaskType;

import javax.persistence.*;

@Entity
@DiscriminatorValue(TaskType.BUG)
public class Bug extends Task {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_id", referencedColumnName = "id")
    private Feature feature;
}
