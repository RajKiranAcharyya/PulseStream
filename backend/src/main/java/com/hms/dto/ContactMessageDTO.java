package com.hms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageDTO {
    private String name;
    private String email;
    private String contact;
    private String message;
}
