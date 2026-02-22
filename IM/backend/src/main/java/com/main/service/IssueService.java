package com.main.service;

import com.main.entity.IssueEntity;
import com.main.enums.IssueStatus;
import com.main.repository.IssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<IssueEntity> getAllIssues() {
        return issueRepository.findAllByOrderByCreatedAtDesc();
    }

    public IssueEntity getIssueById(Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found with id: " + id));
    }

    public List<IssueEntity> getIssuesByStatus(IssueStatus status) {
        return issueRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    public IssueEntity createIssue(IssueEntity issue) {
        return issueRepository.save(issue);
    }

    public IssueEntity updateIssueStatus(Long id, IssueStatus newStatus) {
        IssueEntity issue = getIssueById(id);
        issue.setStatus(newStatus);
        return issueRepository.save(issue);
    }

    public void deleteIssue(Long id) {
        IssueEntity issue = getIssueById(id);
        issueRepository.delete(issue);
    }


}
