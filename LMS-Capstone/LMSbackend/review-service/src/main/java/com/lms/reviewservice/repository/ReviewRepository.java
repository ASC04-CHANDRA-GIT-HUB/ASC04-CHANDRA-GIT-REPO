package com.lms.reviewservice.repository;

import com.lms.reviewservice.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query("SELECT r FROM Review r WHERE r.catalogue_id = :catalogueId")
    List<Review> findByCatalogue_id(@Param("catalogueId") String catalogue_id);

    @Query("SELECT r FROM Review r WHERE r.member_id = :memberId")
    List<Review> findByMember_id(@Param("memberId") String member_id);

    @Query("SELECT r FROM Review r WHERE r.member_id = :memberId AND r.catalogue_id = :catalogueId")
    Optional<Review> findByMember_idAndCatalogue_id(@Param("memberId") String member_id,
                                                    @Param("catalogueId") String catalogue_id);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Review r WHERE r.member_id = :memberId AND r.catalogue_id = :catalogueId")
    boolean existsByMember_idAndCatalogue_id(@Param("memberId") String member_id,
                                             @Param("catalogueId") String catalogue_id);
}
