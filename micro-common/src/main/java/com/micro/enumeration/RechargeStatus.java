package com.micro.enumeration;

public enum RechargeStatus {
    RECHARGING("充值中"),
    SUCCESS("充值成功"),
    FAIL("充值失败");

    private final String description;

    RechargeStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
