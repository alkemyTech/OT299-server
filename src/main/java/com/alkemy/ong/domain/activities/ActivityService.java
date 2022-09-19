package com.alkemy.ong.domain.activities;

import com.alkemy.ong.web.ActivityController;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    private final ActivityGateway activityGateway;

    public ActivityService(ActivityGateway activityGateway) {
        this.activityGateway = activityGateway;
    }

    public void createActivity(ActivityController.ActivityDto activityDto){
        activityGateway.createActivity(activityDto);
    }

}
