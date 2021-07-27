package com.ay.payitbit.service.vonage;

import static com.ay.payitbit.service.vonage.VonageProp.VONAGE_API_KEY;
import static com.ay.payitbit.service.vonage.VonageProp.VONAGE_API_SECRET;

import com.vonage.client.VonageClient;
import javax.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class VonageClientService {

    private VonageClient vonageClient;

    @PostConstruct
    public void init() {
        vonageClient = VonageClient.builder().apiKey(VONAGE_API_KEY).apiSecret(VONAGE_API_SECRET).build();
    }
}
