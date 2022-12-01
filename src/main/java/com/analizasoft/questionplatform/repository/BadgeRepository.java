package com.analizasoft.questionplatform.repository;

import com.analizasoft.questionplatform.domain.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    @Query("SELECT b FROM Badge b WHERE b.requiredNumOfPoints <= :userPoints")
    List<Badge> getUserBadges(Integer userPoints);

}
