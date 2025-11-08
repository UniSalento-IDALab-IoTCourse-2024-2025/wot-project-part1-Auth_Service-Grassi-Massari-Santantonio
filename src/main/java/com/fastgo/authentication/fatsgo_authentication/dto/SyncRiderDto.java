package com.fastgo.authentication.fatsgo_authentication.dto;

public class SyncRiderDto {
    
    private String token;
    private RiderDto rider;

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public RiderDto getRider() {
        return rider;
    }
    public void setRider(RiderDto rider) {
        this.rider = rider;
    }
}
