package com.ay.payitbit.repository;

import com.ay.payitbit.domain.SupplierInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SupplierInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupplierInfoRepository extends JpaRepository<SupplierInfo, Long> {}
