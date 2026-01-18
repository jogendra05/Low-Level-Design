package customer_support.service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import customer_support.enums.IssueType;
import customer_support.models.Agent;
import customer_support.repository.AgentRepository;

public class AgentService {
    private final AgentRepository agentRepository;
    
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }
    
    public void addAgent(String email, String name, List<IssueType> issueTypes){
        String id = "A" + UUID.randomUUID().toString().substring(0, 6);
        Agent agent = new Agent(id, name, email, new HashSet<>(issueTypes));
        agentRepository.save(agent);
        System.out.println(">>> Agent " + id + " created");
    }

    public void viewAgentsWorkHistory() {
        for (Agent agent : agentRepository.getAll()) {
            System.out.println(agent.getId() + " -> " + agent.getHistory());
        }
    }
}
