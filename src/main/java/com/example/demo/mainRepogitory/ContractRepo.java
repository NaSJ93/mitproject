package com.example.demo.mainRepogitory;

import com.example.demo.mainDTO.ContractDTO;
import com.example.demo.mainEntity.Contract;
import com.example.demo.mainEntity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ContractRepo extends JpaRepository<Contract,String> {

    //contract에 vender 연동
   /* @Transactional
    @Modifying
    @Query("UPDATE Contract c SET c.vendor = :vendor WHERE c.CONTRACT_CODE IN :contractCodes")
    void updateVendorForContracts(@Param("vendor") Vendor vendor, @Param("contractCodes") List<String> contractCodes);*/
    //@Query("UPDATE Contract c SET c.vendor = :vendor WHERE c.contractCode IN :contractCodes") 이러면 오류 뜸

    @Query("SELECT p FROM Contract p WHERE p.contractDate BETWEEN :fromDate AND :toDate")
    List<Contract> findByContractDateBetween(Date fromDate, Date toDate);

    @Query(value = "select count(*) from contract",nativeQuery = true)
    int countContract();

}
