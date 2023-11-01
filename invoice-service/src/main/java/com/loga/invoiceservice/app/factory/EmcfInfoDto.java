package com.loga.invoiceservice.app.factory;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmcfInfoDto {
    String nim;
    String status;
    String shopName;
    String address1;
    String address2;
    String address3;
    String contact1;
    String contact2;
    String contact3;
}
