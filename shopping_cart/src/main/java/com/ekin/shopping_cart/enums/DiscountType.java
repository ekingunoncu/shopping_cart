package com.ekin.shopping_cart.enums;

public enum DiscountType {
    RATE("RATE"),
    AMOUNT("AMOUNT");

    private final String value;

    DiscountType(String value) {
        this.value = value;
    }

    public Boolean equalsValue(String value){
        return this.value.equals(value);
    }

    public String toString(){
        return this.value;
    }
}
