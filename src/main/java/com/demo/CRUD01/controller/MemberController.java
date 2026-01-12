package com.demo.CRUD01.controller;

import com.demo.CRUD01.dto.*;
import com.demo.CRUD01.response.MemberGetOneResponse;
import com.demo.CRUD01.response.MemberListInquiryResponseDto;
import com.demo.CRUD01.response.MemberResisterResponseDto;
import com.demo.CRUD01.response.MemberUpdateResponseDto;
import com.demo.CRUD01.request.MemberResiterRequestDto;
import com.demo.CRUD01.request.MemberUpdateRequestDto;
import com.demo.CRUD01.service.MemberAuthInfo;
import com.demo.CRUD01.service.MemberService;
import com.demo.CRUD01.service.jwtService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final jwtService jwtService;
    private MemberService memberService;

    public MemberController(MemberService memberService, jwtService jwtService) {
        this.memberService = memberService;
        this.jwtService = jwtService;
    }

    /**]
     * 회원 가입 API
     */
    @PostMapping
    public ResponseEntity<ApiResponse<MemberResisterResponseDto>> memberResisterApi(@Valid @RequestBody MemberResiterRequestDto memberResiterRequestDto) {

        MemberResisterResponseDto newMember = memberService.resisterMember(memberResiterRequestDto);

        ResponseEntity<ApiResponse<MemberResisterResponseDto>> response = new ResponseEntity<>(new ApiResponse<>("created",201,newMember), HttpStatus.OK);

        return response;
    }

    /**
     * 단 건 조회
     */
    @GetMapping
    public ResponseEntity<ApiResponse<MemberGetOneResponse>> memberGetInquiryApi(
            @RequestHeader("Authorization") String authorization
    ) {
        MemberGetOneResponse memberInfo = memberService.memberGetInquiry(authorization);

        ResponseEntity<ApiResponse<MemberGetOneResponse>> response = new ResponseEntity<>(new ApiResponse<>("success",200,memberInfo),HttpStatus.OK);

        return response;
    }
    /**
     * 다 건 조회
     */
    @GetMapping("/memberListInquiry")
    public ResponseEntity<ApiResponse<MemberListInquiryResponseDto>> memberListApi() {

        MemberListInquiryResponseDto responseDto = memberService.memberList();

        ResponseEntity<ApiResponse<MemberListInquiryResponseDto>> response = new ResponseEntity<>(new ApiResponse<>("success",200,responseDto),HttpStatus.OK);

        return response;
    }
    /**
     * 멤버 수정
     */
    @PatchMapping
    public ResponseEntity<ApiResponse<MemberUpdateResponseDto>> memberUpdateApi(
            @RequestHeader("Authorization") String authorization,
            @RequestBody MemberUpdateRequestDto memberUpdateRequestDto) {

        MemberAuthInfo memberAuthInfo = jwtService.vaildateToken(authorization);
        Long memberId = memberAuthInfo.getMemberId();

        MemberUpdateResponseDto updateMember = memberService.memberUpdate(memberId,memberUpdateRequestDto);

        ResponseEntity<ApiResponse<MemberUpdateResponseDto>> response = new ResponseEntity<>(new ApiResponse<>("success",200,updateMember),HttpStatus.OK);

        return response;
    }
    /**
     * 멤버 삭제
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteMemberApi(
            @RequestHeader("Authorization") String authorization) {

        memberService.memberDelete(authorization);

        ResponseEntity<ApiResponse<Void>> response = new ResponseEntity<>(new ApiResponse<>("deleted",200,null),HttpStatus.OK);

        return response;

    }

}
