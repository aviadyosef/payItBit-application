package com.ay.payitbit.service.vonage;

import static com.ay.payitbit.service.vonage.VonageProp.*;

import com.vonage.client.VonageClient;
import com.vonage.client.account.AccountClient;
import com.vonage.client.account.ListSecretsResponse;
import com.vonage.client.account.SecretResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Data
@Service
public class VonageSecretService {

    private VonageClient vonageClient;

    public VonageSecretService(VonageClientService vonageClientService) {
        this.vonageClient = vonageClientService.getVonageClient();
    }

    public String createSecret() {
        AccountClient accountClient = vonageClient.getAccountClient();

        SecretResponse response = accountClient.createSecret(VONAGE_API_KEY, NEW_SECRET);
        System.out.println(response.getId() + " created at " + response.getCreated());

        return response.getId() + " created at " + response.getCreated();
    }

    public void revokeSecret(String vonageSecretId2Revoke) {
        AccountClient accountClient = vonageClient.getAccountClient();

        accountClient.revokeSecret(VONAGE_API_KEY, vonageSecretId2Revoke);
    }

    public List<String> extractAllSecrets() {
        List<String> secretsList = new ArrayList<>();
        AccountClient accountClient = vonageClient.getAccountClient();

        ListSecretsResponse response = accountClient.listSecrets(VONAGE_API_KEY);
        response.getSecrets().forEach(it -> secretsList.add(it.getId() + " created at " + it.getCreated()));

        return secretsList;
    }

    public String getSecretId(String vonageSecretId) {
        AccountClient accountClient = vonageClient.getAccountClient();
        SecretResponse response = accountClient.getSecret(VONAGE_API_KEY, vonageSecretId);
        log.info(response.getId() + " created at " + response.getCreated());

        return response.getId() + " created at " + response.getCreated();
    }
}
