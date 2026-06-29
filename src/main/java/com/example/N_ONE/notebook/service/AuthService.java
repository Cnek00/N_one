package com.example.N_ONE.notebook.service;

import com.example.N_ONE.notebook.dto.AuthRequest;
import com.example.N_ONE.notebook.entity.User;
import com.example.N_ONE.notebook.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Kullanıcı Kayıt Mantığı
    public String register(AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Bu e-posta adresi zaten kayıtlı kanka!");
        }

        User user = User.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword())) // Şifre hash'lendi!
                .build();

        userRepository.save(user);
        return "Kullanıcı kaydı başarıyla oluşturuldu! Şimdi giriş yapabilirsin. 🚀";
    }

    // Güvenli Giriş Mantığı
    public String login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("E-posta veya şifre hatalı kanka!"));

        // Gelen düz metin şifre ile DB'deki hash'lenmiş şifreyi karşılaştırıyoruz
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("E-posta veya şifre hatalı kanka!");
        }

        return "Giriş başarılı! (JWT Altyapısı Adım 3'te buraya entegre edilecek) 😎";
    }
}