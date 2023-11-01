package com.loga.invoiceservice.app.factory;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusResponseDto {
    Boolean status;
    String version;
    String ifu;
    String nim;
    String tokenValid;
    String serverDateTime;
    Integer pendingRequestsCount;
    List<PendingRequestDto> pendingRequestsList;
}
