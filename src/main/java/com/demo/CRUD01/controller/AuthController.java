package com.demo.CRUD01.controller;


import com.demo.CRUD01.dto.ApiResponse;
import com.demo.CRUD01.request.MemberLoginRequestDto;
import com.demo.CRUD01.response.MemberLoginResponseDto;
import com.demo.CRUD01.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberLoginResponseDto>> loginApi(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
//        log.info("AuthController.loginApi()");

        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();

//        log.info("email: {}", email);
//        log.info("password: {}", password);

       MemberLoginResponseDto responseDto = authService.login(memberLoginRequestDto);

       ResponseEntity<ApiResponse<MemberLoginResponseDto>> response = new ResponseEntity<>(new ApiResponse<>("success",200,responseDto), HttpStatus.OK);

       return response;






    }
}
