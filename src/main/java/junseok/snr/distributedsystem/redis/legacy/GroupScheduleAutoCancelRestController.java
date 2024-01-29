package junseok.snr.distributedsystem.redis.legacy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/autocancel")
public class GroupScheduleAutoCancelRestController {
    private final AutoCancelService autoCancelService;

    @PostMapping("/save")
    public void saveCancelInfo(AutoCancelInfo autoCancelInfo) throws IOException {
        autoCancelService.saveOrUpdateCancelInfo(autoCancelInfo);
    }

    @GetMapping("/get")
    public AutoCancelInfo getCancelInfo(@RequestParam long seqPartnerClassSchedule) throws IOException {
        return autoCancelService.getCancelInfo(seqPartnerClassSchedule);
    }

    @DeleteMapping("/delete")
    public void deleteCancelInfo(@RequestParam long seqPartnerClassSchedule) {
        autoCancelService.deleteCancelInfo(seqPartnerClassSchedule);
    }
}
