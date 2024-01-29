package junseok.snr.distributedsystem.redis.controller.dto;

import java.time.LocalDateTime;

public record UpdateGroupScheduleSettingRequest(
        long seqPartnerClassSchedule,
        LocalDateTime scheduledProcessTime
) {
}
