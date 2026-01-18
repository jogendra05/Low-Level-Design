package customer_support.Strategy;

import java.util.List;

import customer_support.enums.IssueType;
import customer_support.models.Agent;
import customer_support.models.Issue;

public class DefaultAssignmentStrategy implements AssignmentStrategy{
    @Override
    public Agent assign(List<Agent> agents, Issue issue){
        int minIssue = Integer.MAX_VALUE;
        Agent a = null;
        for (Agent agent: agents){
            if (agent.isAvailable() && agent.getExpertise().contains(issue.getIssueType())) return agent;
            if (agent.getExpertise().contains(issue.getIssueType()) && agent.getWaitList().size() < minIssue){
                minIssue = agent.getWaitList().size();
                a = agent;
            }
        }
        return a;
    }
}
