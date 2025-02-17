package com.phamtienanh.identify_service.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.phamtienanh.identify_service.dto.request.AuthenticationRequest;
import com.phamtienanh.identify_service.dto.request.IntrorspectRequest;
import com.phamtienanh.identify_service.dto.response.AuthenticationResponse;
import com.phamtienanh.identify_service.dto.response.IntrospectResponse;
import com.phamtienanh.identify_service.entity.User;
import com.phamtienanh.identify_service.exception.AppRuntimeException;
import com.phamtienanh.identify_service.exception.ErrorCode;
import com.phamtienanh.identify_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    @NonFinal
    @Value("${spring.jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws JOSEException {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new AppRuntimeException(ErrorCode.USER_NOT_FOUND)
        );

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean result = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (result) {
            String token = generateToken(user);

            return AuthenticationResponse.builder()
                    .authenticated(result)
                    .token(token)
                    .build();
        } else throw new AppRuntimeException(ErrorCode.WRONG_PASSWORD);
    }

    public IntrospectResponse introspect(IntrorspectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();

        return IntrospectResponse.builder()
                .valid(expirationDate.after(new Date()) && signedJWT.verify(verifier))
                .build();
    }

    private String buildScope (User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!user.getRoles().isEmpty()) {
            user.getRoles().stream().map(
                    role -> role.getName()
            ).forEach(stringJoiner::add);
        }

        return stringJoiner.toString();
    }

    private String generateToken (User user) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        //payload
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername()) // sub
                .issuer("phamtienanh.com") // iss
                .issueTime(new Date()) // iat
                .expirationTime(new Date(System.currentTimeMillis() + 3600 * 1000)) // exp (1 giờ)
                .claim("scope", buildScope(user)) // Custom claim
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        // tạo chữ ký
        jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));

        return jwsObject.serialize();
    }
}
