package junseok.snr.distributedsystem.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.time.LocalDateTime;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AutoCancelInfo {
    private LocalDateTime cancelTime;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public String toJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    public static AutoCancelInfo fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, AutoCancelInfo.class);
    }
}
