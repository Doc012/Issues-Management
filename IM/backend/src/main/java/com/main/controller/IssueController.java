package com.main.controller;

import com.main.entity.IssueEntity;
import com.main.enums.IssueStatus;
import com.main.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public ResponseEntity<List<IssueEntity>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueEntity> getIssueById(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.getIssueById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<IssueEntity>> getIssuesByStatus(@PathVariable IssueStatus status) {
        return ResponseEntity.ok(issueService.getIssuesByStatus(status));
    }

    @PostMapping
    public ResponseEntity<IssueEntity> createIssue(@Valid @RequestBody IssueEntity issue) {
        return ResponseEntity.status(HttpStatus.CREATED).body(issueService.createIssue(issue));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<IssueEntity> updateIssueStatus(@PathVariable Long id,
                                                          @RequestParam IssueStatus status) {
        return ResponseEntity.ok(issueService.updateIssueStatus(id, status));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
        return ResponseEntity.noContent().build();
    }
}
