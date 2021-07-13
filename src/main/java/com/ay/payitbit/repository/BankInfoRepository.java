package com.ay.payitbit.repository;

import com.ay.payitbit.domain.BankInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the BankInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo, Long> {}
