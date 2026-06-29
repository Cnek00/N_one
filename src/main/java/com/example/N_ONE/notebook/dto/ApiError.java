package com.example.N_ONE.notebook.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    private LocalDateTime timestamp; // Hatanın gerçekleştiği tam zaman
    private int status;              // HTTP Hata Kodu (Örn: 404, 403, 400)
    private String error;            // Hatanın kısa adı (Örn: Not Found)
    private String message;          // Kullanıcıya göstereceğimiz temiz açıklama
    private String path;             // Hatanın patladığı API endpoint uç yolu
}