package com.ay.payitbit.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ay.payitbit.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InvoiceInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceInfo.class);
        InvoiceInfo invoiceInfo1 = new InvoiceInfo();
        invoiceInfo1.setId(1L);
        InvoiceInfo invoiceInfo2 = new InvoiceInfo();
        invoiceInfo2.setId(invoiceInfo1.getId());
        assertThat(invoiceInfo1).isEqualTo(invoiceInfo2);
        invoiceInfo2.setId(2L);
        assertThat(invoiceInfo1).isNotEqualTo(invoiceInfo2);
        invoiceInfo1.setId(null);
        assertThat(invoiceInfo1).isNotEqualTo(invoiceInfo2);
    }
}
