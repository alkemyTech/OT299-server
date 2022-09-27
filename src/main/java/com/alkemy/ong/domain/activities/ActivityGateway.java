package com.alkemy.ong.domain.activities;


public interface ActivityGateway {
    Activity createActivity(Activity activity);
    Activity update(Long id, Activity activity);
}
