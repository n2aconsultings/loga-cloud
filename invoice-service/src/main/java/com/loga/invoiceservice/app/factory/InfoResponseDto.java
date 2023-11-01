package com.loga.invoiceservice.app.factory;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InfoResponseDto {
    Boolean status;
    String version;
    String ifu;
    String nim;
    Date tokenValid;
    Date serverDateTime;
    List<EmcfInfoDto> emcfList;
}
