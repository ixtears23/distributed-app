package junseok.snr.distributedsystem.redis.controller;

import junseok.snr.distributedsystem.redis.controller.dto.CreateGroupScheduleSettingRequest;
import junseok.snr.distributedsystem.redis.controller.dto.DeleteGroupScheduleSettingRequest;
import junseok.snr.distributedsystem.redis.controller.dto.UpdateGroupScheduleSettingRequest;
import junseok.snr.distributedsystem.redis.domain.GroupScheduleSettingValue;
import junseok.snr.distributedsystem.redis.schedule.GroupScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule/setting")
public class GroupScheduleSettingController {
    private final GroupScheduleService groupScheduleService;

    @PostMapping("/get")
    public ResponseEntity<GroupScheduleSettingValue> get(@RequestParam final long seqPartnerClassSchedule) {
        final GroupScheduleSettingValue scheduleSettingValue = groupScheduleService.get(seqPartnerClassSchedule);

        return ResponseEntity.of(Optional.of(scheduleSettingValue));
    }

    @PostMapping("/create")
    public void create(@RequestBody CreateGroupScheduleSettingRequest request) {
        groupScheduleService.create(request);
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateGroupScheduleSettingRequest request) {
        groupScheduleService.update(request);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody DeleteGroupScheduleSettingRequest request) {
    }
}


