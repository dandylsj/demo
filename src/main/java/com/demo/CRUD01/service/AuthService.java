package com.demo.CRUD01.service;

import com.demo.CRUD01.dto.JwtMemberPayload;
import com.demo.CRUD01.request.MemberLoginRequestDto;
import com.demo.CRUD01.response.MemberLoginResponseDto;
import com.demo.CRUD01.entity.Member;
import com.demo.CRUD01.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtService jwtService;


    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, jwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * 로그인 처리
     */
    @Transactional
    public MemberLoginResponseDto login(MemberLoginRequestDto memberLoginRequestDto) {

        //1. 데이터 준비
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();

        //2. 회원 조회 - 받은 email 로 회원을 검색하기
        Member foundMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("없는 이메일 입니다."));
        //3. 비번 검증 - 받아온 비번과 기존 회원 비번이 일치 하는지
        String encodedPassword = foundMember.getPassword();
        boolean match = passwordEncoder.matches(password,encodedPassword);
        if(!match) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        //4. 토큰 만들기
        JwtMemberPayload jwtMemberPayload = new JwtMemberPayload(foundMember.getId(), foundMember.getEmail());
        String encodedJwt = jwtService.createToken(jwtMemberPayload);

        MemberLoginResponseDto responseDto = new MemberLoginResponseDto(encodedJwt);

        return responseDto;
    }


}
