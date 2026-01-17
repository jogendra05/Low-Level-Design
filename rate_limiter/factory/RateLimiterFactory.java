package rate_limiter.factory;

import rate_limiter.enums.RateLimitType;
import rate_limiter.limiter.RateLimiter;
import rate_limiter.limiter.TokenBucketRateLimiter;
import rate_limiter.model.RateLimitConfig;

public class RateLimiterFactory {
    public static RateLimiter createRateLimiter(RateLimitType algo, RateLimitConfig config){
        return switch(algo){
            case TOKEN_BUCKET -> new TokenBucketRateLimiter(config);
            // case FIXED_WINDOW -> 
            default -> throw new IllegalArgumentException("Unkown algorithm " + algo);
        };
    }
}
