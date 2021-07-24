package com.andrumes.ipLocator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.andrumes.ipLocator.model.BannedIp;

@Repository
public interface IbannedIpRepository extends  JpaRepository<BannedIp,Integer> ,JpaSpecificationExecutor<BannedIp> {

}
