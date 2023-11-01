package com.loga.financeservice.app.factory;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private String name;
    private String contact;
    private String address;
    private String type;
    private String legal_notice;
}
