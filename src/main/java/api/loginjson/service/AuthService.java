package api.loginjson.service;

import api.loginjson.dto.AuthRequest;
import api.loginjson.dto.AuthResponse;
import api.loginjson.model.User;
import api.loginjson.repository.JsonUserRepository;
import api.loginjson.security.JwtTokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    private final JsonUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Elimina la inyección de AuthenticationManager y JwtTokenProvider
    public AuthService(JsonUserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse authenticateUser(AuthRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Elimina la llamada a authenticationManager.authenticate()
        // Genera el token directamente
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();

        return new AuthResponse(token);
    }

}
