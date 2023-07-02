package com.ComuniCate.proj.Payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String name;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String taxCode;
    // Passagio di ruoli dal client (Facoltativo)
    private Set<String> roles;
}

