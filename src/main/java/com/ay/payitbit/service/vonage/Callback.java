package com.ay.payitbit.service.vonage;

import static com.ay.payitbit.service.vonage.VonageProp.SMS_CALLBACK_URL;

import com.vonage.client.VonageClient;
import com.vonage.client.account.AccountClient;
import com.vonage.client.account.SettingsResponse;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class Callback {

    private VonageClient vonageClient;

    public Callback(VonageClientService vonageSecretService) {
        this.vonageClient = vonageSecretService.getVonageClient();
    }

    public String getSmsCallbackUrl() {
        AccountClient accountClient = vonageClient.getAccountClient();

        SettingsResponse response = accountClient.updateSmsIncomingUrl(SMS_CALLBACK_URL);
        System.out.println("SMS Callback URL is now " + response.getIncomingSmsUrl());

        return "SMS Callback URL is now " + response.getIncomingSmsUrl();
    }
}
