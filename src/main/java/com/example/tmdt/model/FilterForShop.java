package com.example.tmdt.model;

import com.example.tmdt.model.fkProduct.Brand;
import com.example.tmdt.model.fkProduct.Category;
import com.example.tmdt.model.fkProduct.Shop;

public class FilterForShop {
    private Double maxPrice;
    private Double minPrice;
    private Category category;
    private Brand brand;
    private Shop shop;

    public FilterForShop(Double maxPrice, Double minPrice, Category category, Brand brand, Shop shop) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.category = category;
        this.brand = brand;
        this.shop = shop;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
