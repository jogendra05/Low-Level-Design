package customer_support.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import customer_support.models.Agent;

public class AgentRepository {
    private final Map<String, Agent> agents = new HashMap<>();

    public void save(Agent agent){
        agents.put(agent.getId(), agent);
    }

    public Agent getById(String id){
        return agents.get(id);
    }

    public Collection<Agent> getAll(){
        return agents.values();
    }
}
