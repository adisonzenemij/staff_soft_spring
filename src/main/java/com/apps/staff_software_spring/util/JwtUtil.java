package com.apps.staff_software_spring.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "123456789";  // Clave secreta utilizada para firmar el token
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);  // Algoritmo de firma HMAC con la clave secreta
    private static final String ISSUER = "staff_software_spring";  // Emisor del token
    private static final String AUDIENCE = "your_audience";  // Audiencia destinataria del token


    public String create(String username) {
        return JWT.create()  // Inicia la construcción del token JWT
            .withIssuer(ISSUER)  // Especifica el emisor del token
            .withAudience(AUDIENCE)  // Especifica la audiencia del token
            .withIssuedAt(new Date())  // Establece la fecha y hora de emisión del token
            .withNotBefore(new Date())  // Especifica que el token no debe ser aceptado antes de la fecha y hora actual
            .withExpiresAt(new Date(
                System.currentTimeMillis() +
                TimeUnit.DAYS.toMillis(15)
            ))  // Establece la fecha y hora de expiración del token (15 días desde la emisión)
            .withSubject(username)  // Especifica el sujeto del token (por ejemplo, el nombre de usuario)
            .withJWTId(generateJTI())  // Agrega un identificador único para el token
            .sign(ALGORITHM);  // Firma el token con el algoritmo especificado
    }

    public boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM)  // Inicia el proceso de verificación del token con el algoritmo especificado
                .withIssuer(ISSUER)  // Verifica que el emisor del token coincida
                .withAudience(AUDIENCE)  // Verifica que la audiencia del token coincida
                .build()  // Construye el verificador de JWT
                .verify(jwt);  // Verifica el token JWT proporcionado
            return true;  // Si el token es válido, devuelve true
        } catch (JWTVerificationException e) {
            return false;  // Si el token no es válido, captura la excepción y devuelve false
        }
    }

    public String getCdLogin(String jwt) {
        return JWT.require(ALGORITHM)  // Inicia el proceso de verificación del token con el algoritmo especificado
            .withIssuer(ISSUER)  // Verifica que el emisor del token coincida
            .withAudience(AUDIENCE)  // Verifica que la audiencia del token coincida
            .build()  // Construye el verificador de JWT
            .verify(jwt)  // Verifica el token JWT proporcionado
            .getSubject();  // Devuelve el sujeto del token (por ejemplo, el nombre de usuario)
    }

    private String generateJTI() {
        // Genera un identificador único para el token utilizando UUID
        return java.util.UUID.randomUUID().toString();
    }
}
