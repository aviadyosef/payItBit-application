package com.ay.payitbit.service.vonage;

public class WhatsAppApi {

    public void test1() {
        String text =
            "curl -X POST https://messages-sandbox.nexmo.com/v0.1/messages \\\n" +
            "-u  \\\n" +
            "-H 'Content-Type: application/json' \\\n" +
            "-H 'Accept: application/json' \\\n" +
            "-d '{\n" +
            "    \"from\": { \"type\": \"whatsapp\", \"number\": \"14157386170\" },\n" +
            "    \"to\": { \"type\": \"whatsapp\", \"number\": \"972545968983\" },\n" +
            "    \"message\": {\n" +
            "      \"content\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"text\": \"This is a WhatsApp Message sent from the Messages API\"\n" +
            "      }\n" +
            "    }\n" +
            "  }'";
    }
}
