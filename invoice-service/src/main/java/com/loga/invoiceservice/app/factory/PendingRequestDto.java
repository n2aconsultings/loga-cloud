package com.loga.invoiceservice.app.factory;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PendingRequestDto {
    String uid;
    String date;
}
