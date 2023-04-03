package com.jira.model.entity;

import com.jira.constant.TaskType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@DiscriminatorValue(TaskType.STORY)
public class Story extends Task {
    @OneToMany(mappedBy = "story", fetch = FetchType.LAZY)
    private List<Feature> feature;
}
