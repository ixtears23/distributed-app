package junseok.snr.distributedsystem.scheduler.service;

import junseok.snr.distributedsystem.EmbeddedRedis;
import junseok.snr.distributedsystem.redis.schedule.GroupScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(EmbeddedRedis.class)
@ActiveProfiles("test")
class GroupScheduleServiceTest {
    @Autowired
    private GroupScheduleService groupScheduleService;


    @Test
    void convertKey() {
        final String convertKey = groupScheduleService.convertKey(5);
        final String expectedKey = "job:schedule:group:auto:open:test:5";

        assertEquals(expectedKey, convertKey);
    }
}