package com.alkemy.ong.domain.activities;

import com.alkemy.ong.web.ActivityController;

public interface ActivityGateway {
    void createActivity(ActivityController.ActivityDto activityDto);
}
