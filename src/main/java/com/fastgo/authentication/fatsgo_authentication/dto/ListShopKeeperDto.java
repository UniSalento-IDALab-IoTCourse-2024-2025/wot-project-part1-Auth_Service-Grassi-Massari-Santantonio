package com.fastgo.authentication.fatsgo_authentication.dto;

import java.util.List;

public class ListShopKeeperDto {
    
    private List<ShopKeeperDto> shopKeepers;

    public List<ShopKeeperDto> getShopKeepers() {
        return shopKeepers;
    }
    public void setShopKeepers(List<ShopKeeperDto> shopKeepers) {
        this.shopKeepers = shopKeepers;
    }
}
