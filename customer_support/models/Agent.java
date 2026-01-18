package customer_support.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import customer_support.enums.IssueType;

public class Agent {
    private final String id;
    private final String name;
    private final String email;
    private final Set<IssueType> expertise;

    private String assignedIssueId;
    private final Queue<String> waitList = new LinkedList<>();
    private final List<String> history = new ArrayList<>();

    public Agent(String id, String name, String email, Set<IssueType> expertise) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.expertise = expertise;
    }

    public boolean isAvailable(){
        return assignedIssueId == null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<IssueType> getExpertise() {
        return expertise;
    }

    public String getAssignedIssueId() {
        return assignedIssueId;
    }

    public Queue<String> getWaitList() {
        return waitList;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setAssignedIssueId(String assignedIssueId) {
        this.assignedIssueId = assignedIssueId;
    }

    
}
