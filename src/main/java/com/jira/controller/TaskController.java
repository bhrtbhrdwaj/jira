package com.jira.controller;

import com.jira.service.ITaskService;
import com.jira.service.implementation.BugService;
import com.jira.service.implementation.FeatureService;
import com.jira.service.implementation.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/task")
public class TaskController {
    private final ITaskService bugService;
    private final ITaskService featureService;
    private final ITaskService storyService;
    private final ITaskService taskService;

    @Autowired
    public TaskController(@Qualifier("taskService") ITaskService taskService,
                          @Qualifier("bugService") BugService bugService,
                          @Qualifier("featureService") FeatureService featureService,
                          @Qualifier("storyService") StoryService storyService) {
        this.bugService = bugService;
        this.taskService = taskService;
        this.featureService = featureService;
        this.storyService = storyService;
    }

    @GetMapping("/get")
    public String a() {
        return "a";
    }
    @PostMapping("/post")
    public void a(@RequestBody List<Integer> nums) {

    }
}
