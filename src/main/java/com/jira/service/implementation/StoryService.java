package com.jira.service.implementation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("storyService")
public class StoryService extends TaskService {
    public void addFeature() { }
}
