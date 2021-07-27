package com.ay.payitbit.web.rest.vonage;

import com.ay.payitbit.domain.BankInfo;
import com.ay.payitbit.service.dto.vonageDto.VonageBalance;
import com.ay.payitbit.service.vonage.Callback;
import com.ay.payitbit.service.vonage.GetBalanceService;
import com.ay.payitbit.service.vonage.VonageSecretService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link BankInfo}.
 */
@RestController
@RequestMapping("/api")
public class VonageController {

    private final Logger log = LoggerFactory.getLogger(VonageController.class);

    private final GetBalanceService getBalanceService;
    private final Callback callback;
    private final VonageSecretService vonageSecretService;

    public VonageController(GetBalanceService getBalanceService, Callback callback, VonageSecretService vonageSecretService) {
        this.getBalanceService = getBalanceService;
        this.callback = callback;
        this.vonageSecretService = vonageSecretService;
    }

    /**
     * {@code GET  /bank-infos} : get all the bankInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bankInfos in body.
     */
    @GetMapping("/vonage-get-balance")
    public VonageBalance getVonageBalance() {
        log.debug("REST request to get all BankInfos");
        return getBalanceService.extractVonageBalance();
    }

    @GetMapping("/vonage-set-SmsCallbackUrl")
    public String setSmsCallbackUrl() {
        log.debug("REST request to set all SmsCallbackUrl");
        return callback.getSmsCallbackUrl();
    }

    @GetMapping("/vonage-get-SmsCallbackUrl")
    public void getSmsCallbackUrl(String input) {
        log.debug("REST request to get all SmsCallbackUrl!!!!!!");
        log.info("input got = " + input);
    }

    @GetMapping("/vonage-create-new-secret")
    public void getCreateSecret(String input) {
        log.debug("REST request to get all CreateSecret");
        String createSecret = vonageSecretService.createSecret();
        log.info("CreateSecret=" + createSecret);
    }

    @GetMapping("/vonage-revoke-secret")
    public void triggerRevokeSecret(String vonageSecretId2Revoke) {
        log.debug("REST request to revokeSecret");
        vonageSecretService.revokeSecret(vonageSecretId2Revoke);
        log.info("RevokeSecret" + vonageSecretId2Revoke + " done.");
    }

    @GetMapping("/vonage-all-secret")
    public List<String> extractAllSecrets() {
        log.debug("REST request to extractAllSecrets");
        List<String> allSecrets = vonageSecretService.extractAllSecrets();
        log.info("extractAllSecrets done.");

        return allSecrets;
    }

    @GetMapping("/vonage-get-secret-info")
    public String getVonageSecretId(String vonageSecretId) {
        log.debug("REST request to get vonageSecretId info");
        String secretId = vonageSecretService.getSecretId(vonageSecretId);
        log.info("get vonageSecretId info" + vonageSecretId + " ");

        return secretId;
    }
}
