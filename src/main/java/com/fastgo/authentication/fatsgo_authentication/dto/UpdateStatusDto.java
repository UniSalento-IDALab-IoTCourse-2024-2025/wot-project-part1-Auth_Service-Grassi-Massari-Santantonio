package com.fastgo.authentication.fatsgo_authentication.dto;

import com.fastgo.authentication.fatsgo_authentication.domain.State;

public class UpdateStatusDto {

    private String userId;
    private State newStatus;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public State getNewStatus() {
        return newStatus;
    }
    public void setNewStatus(State newStatus) {
        this.newStatus = newStatus;
    }
}
