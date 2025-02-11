package com.phamtienanh.identify_service.controller;

import com.nimbusds.jose.JOSEException;
import com.phamtienanh.identify_service.dto.request.ApiResponse;
import com.phamtienanh.identify_service.dto.request.AuthenticationRequest;
import com.phamtienanh.identify_service.dto.request.IntrorspectRequest;
import com.phamtienanh.identify_service.dto.response.AuthenticationResponse;
import com.phamtienanh.identify_service.dto.response.IntrospectResponse;
import com.phamtienanh.identify_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse <AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws JOSEException {

        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);

        return  ApiResponse.<AuthenticationResponse>builder()
                .code(200)
                .result(authenticationResponse)
                .build();
    }


    @PostMapping("/introspect")
    ApiResponse <IntrospectResponse> introspect (@RequestBody IntrorspectRequest intorspectRequest) throws JOSEException, ParseException {

        IntrospectResponse response = authenticationService.introspect(intorspectRequest);

        return  ApiResponse.<IntrospectResponse>builder()
                .code(200)
                .result(response)
                .build();
    }


}
