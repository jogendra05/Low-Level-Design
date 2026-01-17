package rate_limiter.service;

import java.util.HashMap;
import java.util.Map;

import rate_limiter.enums.RateLimitType;
import rate_limiter.enums.UserTier;
import rate_limiter.factory.RateLimiterFactory;
import rate_limiter.limiter.RateLimiter;
import rate_limiter.model.RateLimitConfig;
import rate_limiter.model.User;

public class RateLimiterServies {
    private final Map<UserTier, RateLimiter> rateLimiter = new HashMap<>();

    public RateLimiterServies(){
        rateLimiter.put(UserTier.FREE, 
            RateLimiterFactory.createRateLimiter(RateLimitType.TOKEN_BUCKET, 
                new RateLimitConfig(10, 60))
        );
    }

    public boolean allowRequest(User user){
        RateLimiter limiter = rateLimiter.get(user.getTier());
        if (limiter == null){
            throw new IllegalArgumentException("No limiter configured for tier " + user.getTier());
        }
        return limiter.allowRequest(user.getUserId());
    }
}
