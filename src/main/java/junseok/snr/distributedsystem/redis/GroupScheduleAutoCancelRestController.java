package junseok.snr.distributedsystem.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/autocancel")
public class GroupScheduleAutoCancelRestController {
    private final AutoCancelService autoCancelService;

    @PostMapping("/save")
    public void saveCancelInfo(@RequestParam String profile,
                               @RequestParam long seqPartnerClassSchedule,
                               @RequestBody AutoCancelInfo info) throws IOException {
        autoCancelService.saveOrUpdateCancelInfo(profile, seqPartnerClassSchedule, info);
    }

    @GetMapping("/get")
    public AutoCancelInfo getCancelInfo(@RequestParam String profile,
                                        @RequestParam long seqPartnerClassSchedule) throws IOException {
        return autoCancelService.getCancelInfo(profile, seqPartnerClassSchedule);
    }

    @DeleteMapping("/delete")
    public void deleteCancelInfo(@RequestParam String profile,
                                 @RequestParam long seqPartnerClassSchedule) {
        autoCancelService.deleteCancelInfo(profile, seqPartnerClassSchedule);
    }
}
