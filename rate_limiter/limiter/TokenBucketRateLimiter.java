package rate_limiter.limiter;

import rate_limiter.enums.RateLimitType;
import rate_limiter.model.RateLimitConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter extends RateLimiter{
    private Map<String, Integer> tokens = new ConcurrentHashMap<>();
    private Map<String, Long> lastRefillTime = new HashMap<>();

    public TokenBucketRateLimiter(RateLimitConfig config){
        super(config, RateLimitType.TOKEN_BUCKET);
    }

    @Override
    public boolean allowRequest(String userId){
        long now = System.currentTimeMillis();
        int currentToken = refillTokens(userId, now);

        if (currentToken > 0){
            tokens.put(userId, currentToken-1);
            return true;
        }
        return false;
    }

    private int refillTokens(String userId, long now){
        double refillRate = (double) config.getWindowInSeconds() / config.getMaxRequests();

        long lastRefill = lastRefillTime.getOrDefault(userId, now);
        long elapsedSeconds = (now - lastRefill) / 1000;
        int refillTokens = (int) (elapsedSeconds / refillRate);

        int currentTokens = tokens.getOrDefault(userId, config.getMaxRequests());
        currentTokens = Math.min(config.getMaxRequests(), currentTokens + refillTokens);
        if (refillTokens > 0) lastRefillTime.put(userId, now);

        return currentTokens;
    }
}
