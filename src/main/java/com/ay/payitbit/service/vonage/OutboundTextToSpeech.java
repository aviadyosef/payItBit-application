/*
 * Copyright  2020 Vonage
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ay.payitbit.service.vonage;

import com.vonage.client.VonageClient;
import com.vonage.client.voice.Call;

public class OutboundTextToSpeech {

    public void init() {
        //        configureLogging();

        //        final String VONAGE_APPLICATION_ID = "Vnq9Jl6V1qBId2i1";
        //        final String VONAGE_PRIVATE_KEY_PATH = "4c739008";

        //        VonageClient client = VonageClient.builder()
        //            .applicationId(VonageProp.VONAGE_APPLICATION_ID)
        //            .privateKeyPath(VONAGE_PRIVATE_KEY_PATH)
        //            .build();

        VonageClient client = VonageClient
            .builder()
            .applicationId(VonageProp.VONAGE_API_SECRET)
            .privateKeyPath(VonageProp.VONAGE_API_KEY)
            .build();

        final String VONAGE_NUMBER = VonageProp.VONAGE_NUMBER;
        final String TO_NUMBER = VonageProp.TO_NUMBER;
        final String ANSWER_URL = "https://nexmo-community.github.io/ncco-examples/talk.json";

        client.getVoiceClient().createCall(new Call(TO_NUMBER, VONAGE_NUMBER, ANSWER_URL));
    }
}
