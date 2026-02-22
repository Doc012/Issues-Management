package com.main.repository;

import com.main.entity.IssueEntity;
import com.main.enums.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Long> {
    List<IssueEntity> findByStatus(IssueStatus status);
    List<IssueEntity> findAllByOrderByCreatedAtDesc();
    List<IssueEntity> findByStatusOrderByCreatedAtDesc(IssueStatus status);
}
