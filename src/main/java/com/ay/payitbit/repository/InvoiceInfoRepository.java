package com.ay.payitbit.repository;

import com.ay.payitbit.domain.InvoiceInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InvoiceInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceInfoRepository extends JpaRepository<InvoiceInfo, Long> {}
