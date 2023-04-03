package com.jira.service.implementation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("bugService")
public class BugService extends TaskService {
    public void linkToATask() {

    }
}
