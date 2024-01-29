package junseok.snr.distributedsystem.redis.legacy;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EnvironmentService {
    private final Environment environment;

    public String getActiveProfile() {
        return environment.getActiveProfiles()[0];
    }
}
