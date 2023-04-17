package backend.softdle.rest.dtos;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
 @AllArgsConstructor
@Setter
@Getter

public class VerifyDto {
  private String token;
}
