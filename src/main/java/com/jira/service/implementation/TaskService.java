package com.jira.service.implementation;

import com.jira.model.entity.Task;
import com.jira.service.ITaskService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("taskService")
public class TaskService implements ITaskService {
    @Override
    public void create() {

    }

    @Override
    public void assign() {

    }

    @Override
    public void getTaskDetails() {

    }

    @Override
    public void updateStatus() {

    }

    @Override
    public List<Task> getDelayedTask() {
        return null;
    }

    @Override
    public List<Task> getUserTask() {
        return null;
    }
}
