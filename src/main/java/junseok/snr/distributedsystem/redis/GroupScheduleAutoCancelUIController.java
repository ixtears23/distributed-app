package junseok.snr.distributedsystem.redis;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/autocancel")
public class GroupScheduleAutoCancelUIController {
    @GetMapping("/")
    public String autoCancelForm(Model model) {
        model.addAttribute("autoCancelInfo", new AutoCancelInfo());
        return "autocancel";
    }
}
