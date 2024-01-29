package junseok.snr.distributedsystem.redis.schedule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import junseok.snr.distributedsystem.redis.controller.dto.CreateGroupScheduleSettingRequest;
import junseok.snr.distributedsystem.redis.controller.dto.UpdateGroupScheduleSettingRequest;
import junseok.snr.distributedsystem.redis.domain.GroupScheduleSettingValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupScheduleService {
    private static final String JOB_PARTNER_CLASS_SCHEDULE_AUTO_OPEN_KEY = "job:schedule:group:auto:open:{profile}:{seqPartnerClassSchedule}";
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    public GroupScheduleSettingValue get(final long seqPartnerClassSchedule) {
        final String key = buildKey(seqPartnerClassSchedule);
        final HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        final Map<String, String> entries = hashOperations.entries(key);

        if (entries.isEmpty()) {
            throw new RuntimeException("=== No data exists for the specified key.");
        }
        return objectMapper.convertValue(entries, GroupScheduleSettingValue.class);
    }

    public void create(final CreateGroupScheduleSettingRequest request) {
        final String key = buildKey(request.seqPartnerClassSchedule());

        final GroupScheduleSettingValue setting = GroupScheduleSettingValue.createSetting(request.seqPartnerClassSchedule(), request.scheduledProcessTime());
        final Map<String, String> settingMap = objectMapper.convertValue(setting, new TypeReference<>() {});

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, settingMap);
    }

    public void update(final UpdateGroupScheduleSettingRequest request) {
        final String key = buildKey(request.seqPartnerClassSchedule());

        final GroupScheduleSettingValue getSettingValue = get(request.seqPartnerClassSchedule());

        final GroupScheduleSettingValue settingValue = GroupScheduleSettingValue.updateSetting(request.seqPartnerClassSchedule(), getSettingValue.createTime(), getSettingValue.scheduledProcessTime());
        final Map<String, String> settingMap = objectMapper.convertValue(settingValue, new TypeReference<>() {});

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, settingMap);
    }

    public void delete() {

    }

    public List<?> getAll() {
        return null;
    }

    public String buildKey(final long seqPartnerClassSchedule) {
        final String key = JOB_PARTNER_CLASS_SCHEDULE_AUTO_OPEN_KEY
                .replace("{profile}", activeProfile)
                .replace("{seqPartnerClassSchedule}", Long.toString(seqPartnerClassSchedule));
        log.info("=== key : {}", key);
        return key;
    }


}
