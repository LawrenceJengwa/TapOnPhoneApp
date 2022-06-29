package com.wizzitdigital.emv.sdk.debicheck;

public interface EMVDebicheckJavaListener {
    void onDebicheckComplete(Boolean success, String code, String complete);
}