package com.ay.payitbit.service.vonage;

import static com.ay.payitbit.service.vonage.VonageProp.VONAGE_API_KEY;
import static com.ay.payitbit.service.vonage.VonageProp.VONAGE_API_SECRET;

import com.ay.payitbit.service.dto.vonageDto.VonageBalance;
import com.vonage.client.VonageClient;
import com.vonage.client.account.AccountClient;
import com.vonage.client.account.BalanceResponse;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class GetBalanceService {

    public VonageBalance extractVonageBalance() {
        VonageClient client = VonageClient.builder().apiKey(VONAGE_API_KEY).apiSecret(VONAGE_API_SECRET).build();

        AccountClient accountClient = client.getAccountClient();

        BalanceResponse response = accountClient.getBalance();
        //        VonageBalance vonageBalance = VonageBalance.builder()
        //                                            .balance   ("Balance: %s EUR "     + response.getValue())
        //                                            .autoReload("Auto-reload Enabled:" + response.isAutoReload())
        //                                            .build();

        VonageBalance vonageBalance = new VonageBalance();
        vonageBalance.setBalance("Balance: %s EUR " + response.getValue());
        vonageBalance.setAutoReload("Auto-reload Enabled:" + response.isAutoReload());

        System.out.printf("Balance: %s EUR\n", response.getValue());
        System.out.printf("Auto-reload Enabled: %s\n", response.isAutoReload());

        return vonageBalance;
    }
}
