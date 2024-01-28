package junseok.snr.distributedsystem.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AutoCancelService {
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public AutoCancelService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveOrUpdateCancelInfo(String profile, long seqPartnerClassSchedule, AutoCancelInfo info) throws IOException {
        String key = createKey(profile, seqPartnerClassSchedule);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key, info.toJson());
        redisTemplate.expire(key, Duration.between(LocalDateTime.now(), info.getCancelTime()).plusHours(24));
    }

    public AutoCancelInfo getCancelInfo(String profile, long seqPartnerClassSchedule) throws IOException {
        String key = createKey(profile, seqPartnerClassSchedule);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String data = ops.get(key);
        return AutoCancelInfo.fromJson(data);
    }

    public void deleteCancelInfo(String profile, long seqPartnerClassSchedule) {
        String key = createKey(profile, seqPartnerClassSchedule);
        redisTemplate.delete(key);
    }

    private String createKey(String profile, long seqPartnerClassSchedule) {
        return "job:autocancel:" + profile + ":" + seqPartnerClassSchedule;
    }
}
