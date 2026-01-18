package customer_support.Strategy;

import java.util.List;

import customer_support.models.Agent;
import customer_support.models.Issue;

public interface AssignmentStrategy {
    Agent assign(List<Agent> agents, Issue issue);
}
