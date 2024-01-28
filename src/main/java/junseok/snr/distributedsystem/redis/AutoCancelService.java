package junseok.snr.distributedsystem.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AutoCancelService {
    private final EnvironmentService environmentService;
    private final StringRedisTemplate redisTemplate;

    public void saveOrUpdateCancelInfo(AutoCancelInfo info) throws IOException {
        String key = createKey(info.getSeqPartnerClassSchedule());
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(key, info.toJson());
        redisTemplate.expire(key, Duration.between(LocalDateTime.now(), info.getCancelTime()).plusHours(24));
    }

    public AutoCancelInfo getCancelInfo(long seqPartnerClassSchedule) throws IOException {
        String key = createKey(seqPartnerClassSchedule);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String data = ops.get(key);
        return AutoCancelInfo.fromJson(data);
    }

    public void deleteCancelInfo(long seqPartnerClassSchedule) {
        String key = createKey(seqPartnerClassSchedule);
        redisTemplate.delete(key);
    }

    private String createKey(long seqPartnerClassSchedule) {
        return "job:autocancel:" + environmentService.getActiveProfile() + ":" + seqPartnerClassSchedule;
    }
}
