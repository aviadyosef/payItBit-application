package com.ay.payitbit.service.vonage;

import lombok.Data;

@Data
public class VonageProp {

    protected static final String VONAGE_API_KEY = "4c739008";
    protected static final String VONAGE_API_SECRET = "Vnq9Jl6V1qBId2i1";

    protected static final String VONAGE_NUMBER = "14157386170";
    protected static final String TO_NUMBER = "972545968983";

    protected static final String SMS_CALLBACK_URL = "http://10.0.0.4:8080/api/vonage-get-SmsCallbackUrl";
    //    protected static final String SMS_CALLBACK_URL = "http://localhost:8080/api/vonage-get-SmsCallbackUrl";

    // 894e4aae-9d75-4666-9a35-566105145da2
    protected static String NEW_SECRET = "0123Jl6V1qBId2i1";

    protected static String VONAGE_SECRET_ID = "0123Jl6V1qBId2i1";
}
