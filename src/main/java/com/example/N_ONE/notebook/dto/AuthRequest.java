package com.example.N_ONE.notebook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @Email(message = "Geçerli bir e-posta adresi giriniz kanka!")
    @NotBlank(message = "E-posta alanı boş bırakılamaz.")
    private String email;

    @NotBlank(message = "Şifre alanı boş bırakılamaz.")
    private String password;
}