package com.wizzitdigital.emv.sdk.debicheck;

import android.app.Activity;

import com.wizzitdigital.emv.sdk.debicheck.EMVDebicheck;

public class EMVDebicheckJava {
    EMVDebicheck emvDebicheck;
    EMVDebicheckJavaListener listener;

    public EMVDebicheckJava(Activity activity, EMVDebicheckJavaListener listener) {
        emvDebicheck = new EMVDebicheck(activity);
        this.listener = listener;
    }

    public void initialize(
            String contractNumber,
            String mobileNumber,
            String accountNumber,
            AccountType accountType,
            IdType idType,
            String rsaIdNumber,
            Integer installmentAmount,
            Integer collectionAmount,
            Integer maxAmount
    ) {
        EMVContractInfo contractInfo = new EMVContractInfo(
                contractNumber,
                mobileNumber,
                accountNumber,
                accountType,
                idType,
                rsaIdNumber,
                installmentAmount,
                collectionAmount,
                maxAmount
        );
        emvDebicheck.initialize(contractInfo,
                (Boolean success, String code, String complete) -> {
                    listener.onDebicheckComplete(success, code, complete);
                    return null;
                }
        );
    }

}


