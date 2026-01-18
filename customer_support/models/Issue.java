package customer_support.models;

import java.util.UUID;

import customer_support.enums.IssueStatus;
import customer_support.enums.IssueType;

public class Issue {
    private final String id;
    private final String transactionId;
    private final IssueType issueType;
    private final String subject;
    private final String description;
    private final String email;
    private IssueStatus status;
    private String resolution;
    private String assignedAgentId;

    public Issue(String transactionId, IssueType issueType, String subject, String description, String email) {
        this.id = "I"+ UUID.randomUUID().toString().substring(0, 6);
        this.transactionId = transactionId;
        this.issueType = issueType;
        this.subject = subject;
        this.description = description;
        this.email = email;
        this.status = IssueStatus.OPEN;
    }

    @Override
    public String toString(){
        return id + " {\"" + transactionId + "\", \"" + issueType + "\", \"" + subject + "\", \"" + description + "\", \"" + email + "\", \"" + status + "\"}";
    }

    public String getId() {
        return id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public IssueType getIssueType() {
        return issueType;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public String getResolution() {
        return resolution;
    }

    public String getAssignedAgentId() {
        return assignedAgentId;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setAssignedAgentId(String assignedAgentId) {
        this.assignedAgentId = assignedAgentId;
    }
}
