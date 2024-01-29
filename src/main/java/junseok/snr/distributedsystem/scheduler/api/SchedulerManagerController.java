package junseok.snr.distributedsystem.scheduler.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/schedule/manager")
public class SchedulerManagerController {

    @PostMapping("/add")
    public void addSchedule(@RequestBody Map<String, Object> map) {

    }

    @PostMapping("/change")
    public void changeSchedule(@RequestBody Map<String, Object> map) {

    }


    @PostMapping("/delete")
    public void deleteSchedule(@RequestBody Map<String, Object> map) {

    }

}
