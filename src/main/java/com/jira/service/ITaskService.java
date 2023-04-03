package com.jira.service;

import java.util.List;

public interface ITaskService {
    void create();
    void assign();
    void getTaskDetails();
    void updateStatus();
    List getDelayedTask();
    List getUserTask();
}
