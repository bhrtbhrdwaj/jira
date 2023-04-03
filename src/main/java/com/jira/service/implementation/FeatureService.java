package com.jira.service.implementation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("featureService")
public class FeatureService extends TaskService {
}
