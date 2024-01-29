package junseok.snr.distributedsystem.redis.controller.dto;

import java.time.LocalDateTime;

public record CreateGroupScheduleSettingRequest(
        long seqPartnerClassSchedule,
        LocalDateTime scheduledProcessTime
) {
}
