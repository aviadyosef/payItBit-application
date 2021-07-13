package com.ay.payitbit.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ay.payitbit.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SupplierInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplierInfo.class);
        SupplierInfo supplierInfo1 = new SupplierInfo();
        supplierInfo1.setId(1L);
        SupplierInfo supplierInfo2 = new SupplierInfo();
        supplierInfo2.setId(supplierInfo1.getId());
        assertThat(supplierInfo1).isEqualTo(supplierInfo2);
        supplierInfo2.setId(2L);
        assertThat(supplierInfo1).isNotEqualTo(supplierInfo2);
        supplierInfo1.setId(null);
        assertThat(supplierInfo1).isNotEqualTo(supplierInfo2);
    }
}
