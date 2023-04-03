package com.jira.model.entity;

import com.jira.constant.TaskType;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue(TaskType.FEATURE)
public class Feature extends Task {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;
    @OneToMany(mappedBy = "feature", fetch = FetchType.LAZY)
    private List<Bug> bugs;
}
