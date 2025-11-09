package com.fastgo.authentication.fatsgo_authentication.dto;


import com.fastgo.authentication.fatsgo_authentication.domain.Role;
import com.fastgo.authentication.fatsgo_authentication.domain.State;


public class ShopKeeperDto {
    private String id;
    private String name;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Role role = Role.SHOPKEEPER;
    private State status;
    private String pictureUrl;

    //Restaurrant info
    private String restaurantName;
    private String restaurantCity;  
    private String restaurantProvince;
    private String restaurantAddress; 
    private String restaurantPostalCode;
    private String restaurantVatNumber;
    private String latitude;
    private String longitude;
    private String restaurantPhoneNumber;


    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Role getRole() {
        return role;
    }
   
    public State getStatus() {
        return status;
    }
    public void setStatus(State status) {
        this.status = status;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public String getRestaurantCity() {
        return restaurantCity;
    }
    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }
    public String getRestaurantProvince() {
        return restaurantProvince;
    }
    public void setRestaurantProvince(String restaurantProvince) {
        this.restaurantProvince = restaurantProvince;
    }
    public String getRestaurantAddress() {
        return restaurantAddress;
    }
    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }
    public String getRestaurantPostalCode() {
        return restaurantPostalCode;
    }
    public void setRestaurantPostalCode(String restaurantPostalCode) {
        this.restaurantPostalCode = restaurantPostalCode;
    }
    public String getRestaurantVatNumber() {
        return restaurantVatNumber;
    }
    public void setRestaurantVatNumber(String restaurantVatNumber) {
        this.restaurantVatNumber = restaurantVatNumber;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRestaurantPhoneNumber() {
        return restaurantPhoneNumber;
    }
    public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
    }

}