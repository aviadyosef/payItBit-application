package com.ay.payitbit.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ay.payitbit.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BankInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankInfo.class);
        BankInfo bankInfo1 = new BankInfo();
        bankInfo1.setId(1L);
        BankInfo bankInfo2 = new BankInfo();
        bankInfo2.setId(bankInfo1.getId());
        assertThat(bankInfo1).isEqualTo(bankInfo2);
        bankInfo2.setId(2L);
        assertThat(bankInfo1).isNotEqualTo(bankInfo2);
        bankInfo1.setId(null);
        assertThat(bankInfo1).isNotEqualTo(bankInfo2);
    }
}
