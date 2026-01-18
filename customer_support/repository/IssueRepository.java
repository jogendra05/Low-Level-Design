package customer_support.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import customer_support.models.Issue;

public class IssueRepository {
    private final Map<String, Issue> issues = new HashMap<>();

    public void save(Issue issue){
        issues.put(issue.getId(), issue);
    }

    public Issue getById(String id){
        return issues.get(id);
    }

    public Collection<Issue> getAll(){
        return issues.values();
    }
}
