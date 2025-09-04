package com.lms.circulationservice.repository;

import com.lms.circulationservice.model.Circulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CirculationRepository extends JpaRepository<Circulation, String> {
    List<Circulation> findByDeletedFalse();

    @Query("SELECT c FROM Circulation c WHERE c.catalogue_id = :catalogueId AND c.status = :status")
    Optional<Circulation> findByCatalogue_idAndStatus(@Param("catalogueId") String catalogueId,
                                                      @Param("status") String status);

    @Query("SELECT c FROM Circulation c WHERE c.catalogue_id = :catalogueId AND c.member_id = :memberId AND c.status = :status")
    Optional<Circulation> findByCatalogue_idAndMember_idAndStatus(@Param("catalogueId") String catalogueId,
                                                                  @Param("memberId") String memberId,
                                                                  @Param("status") String status);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Circulation c " +
            "WHERE c.catalogue_id = :catalogueId AND c.member_id = :memberId AND c.status = :status")
    boolean existsByMember_idAndCatalogue_idAndStatus(@Param("memberId") String memberId,
                                                      @Param("catalogueId") String catalogueId,
                                                      @Param("status") String status);

}
