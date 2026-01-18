package customer_support.service;

import java.util.List;
import java.util.Map;

import customer_support.enums.IssueStatus;
import customer_support.enums.IssueType;
import customer_support.models.Agent;
import customer_support.models.Issue;
import customer_support.repository.AgentRepository;
import customer_support.repository.IssueRepository;

public class IssueService {
    private final IssueRepository issueRepository;
    private final AgentRepository agentRepository;

    public IssueService(IssueRepository issueRepository, AgentRepository agentRepository) {
        this.issueRepository = issueRepository;
        this.agentRepository = agentRepository;
    }

    public Issue createIssue(String transactionId, IssueType issueType, String subject, String description, String email){
        Issue issue = new Issue(transactionId, issueType, subject, description, email);
        issueRepository.save(issue);
        System.out.println(">>> Issue " + issue.getId() + " created against transaction \"" + transactionId + "\"");
        return issue;
    }

    // Possible filter
    // {"email": "testUser2@test.com"}
    // {"type": "Payment Related"}
    // {"status": "open"}
    public List<Issue> getIssues(Map<String, String> filter){
        return issueRepository.getAll().stream().filter(issue -> {
            if(filter.containsKey("email") && !issue.getEmail().equalsIgnoreCase(filter.get("email"))){
                return false;
            }
            if (filter.containsKey("type") && !issue.getIssueType().name().equalsIgnoreCase(filter.get("type"))){
                return false;
            }
            if (filter.containsKey("status") && !issue.getStatus().name().equalsIgnoreCase(filter.get("status"))){
                return false;
            }
            return true;
        }).toList();
    }

    public void updateIssue(String issueId, IssueStatus status, String resolution) {
        Issue issue = issueRepository.getById(issueId);
        if (issue == null) throw new IllegalArgumentException("Issue not found");
        issue.setStatus(status);
        issue.setResolution(resolution);
        System.out.println(">>> " + issueId + " status updated to " + issue.getStatus());
    }

    public void resolveIssue(String issueId, String resolution) {
        Issue issue = issueRepository.getById(issueId);
        if (issue == null) throw new IllegalArgumentException("Issue not found");

        issue.setStatus(IssueStatus.RESOLVED);
        issue.setResolution(resolution);

        if (issue.getAssignedAgentId() != null) {
            Agent agent = agentRepository.getById(issue.getAssignedAgentId());
            if (agent != null) {
                agent.getHistory().add(issue.getId());
                agent.setAssignedIssueId(null);
            }
        }

        System.out.println(">>> " + issueId + " issue marked resolved");
    }


}
