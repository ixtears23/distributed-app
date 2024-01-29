package junseok.snr.distributedsystem.redis.domain;

import java.time.LocalDateTime;

public record GroupScheduleSettingValue(
        long seqPartnerClassSchedule,
        State state,
        LocalDateTime scheduledProcessTime,
        LocalDateTime processingStartTime,
        LocalDateTime processingEndTime,
        LocalDateTime createTime,
        LocalDateTime lastUpdatedTime
) {

    public static GroupScheduleSettingValue createSetting(long seqPartnerClassSchedule, LocalDateTime scheduledProcessTime) {
        return new GroupScheduleSettingValue(
                seqPartnerClassSchedule,
                State.PENDING,
                scheduledProcessTime,
                null,
                null,
                LocalDateTime.now(),
                null
        );
    }

    public static GroupScheduleSettingValue updateSetting(long seqPartnerClassSchedule, LocalDateTime createTime, LocalDateTime scheduledProcessTime) {
        return new GroupScheduleSettingValue(
                seqPartnerClassSchedule,
                State.PENDING,
                scheduledProcessTime,
                null,
                null,
                createTime,
                LocalDateTime.now()
        );
    }

    public enum State {
        PENDING,
        PROCESSING,
        COMPLETED
    }
}
