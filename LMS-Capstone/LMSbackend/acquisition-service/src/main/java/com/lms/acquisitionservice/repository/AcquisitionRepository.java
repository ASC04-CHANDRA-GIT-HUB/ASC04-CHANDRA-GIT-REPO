package com.lms.acquisitionservice.repository;


import com.lms.acquisitionservice.model.Acquisition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquisitionRepository extends JpaRepository<Acquisition, String> {}
