package com.demo.CRUD01.service;

import com.demo.CRUD01.response.MemberGetOneResponse;
import com.demo.CRUD01.response.MemberListInquiryResponseDto;
import com.demo.CRUD01.response.MemberResisterResponseDto;
import com.demo.CRUD01.response.MemberUpdateResponseDto;
import com.demo.CRUD01.entity.Member;
import com.demo.CRUD01.repository.MemberRepository;
import com.demo.CRUD01.request.MemberResiterRequestDto;
import com.demo.CRUD01.request.MemberUpdateRequestDto;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtService jwtService;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, jwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * 회원가입 로직
     * @param
     */
    @Transactional
    public MemberResisterResponseDto resisterMember(@Valid MemberResiterRequestDto memberResiterRequestDto) {

//        String name = memberCreateRequestDto.getName();
//        String email = memberCreateRequestDto.getEmail();
//        String password = memberCreateRequestDto.getPassword();

        //2. 이메일 중복여부 체크
        if (memberRepository.existsByEmail(memberResiterRequestDto.getEmail())) {
            throw new RuntimeException("이미 있는 이메일인데요");
        }

        //3.비밀번호 암호화
       String encodedPassword = passwordEncoder.encode(memberResiterRequestDto.getPassword());

       Member newMember = new Member(
               memberResiterRequestDto.getName(),
               memberResiterRequestDto.getEmail(),
               encodedPassword);

        Member saveMember = memberRepository.save(newMember);

        MemberResisterResponseDto responseDto = new MemberResisterResponseDto(saveMember.getId());

        return responseDto;
    }

    /**
     * 단 건 조회
     */
    @Transactional(readOnly = true)
    public MemberGetOneResponse memberGetInquiry(String authorization) {
        //1.조회하는 아이디와 로그인한 아이디가 같은 사람인지 검증
        MemberAuthInfo memberAuthInfo = jwtService.vaildateToken(authorization);
        Long memberId = memberAuthInfo.getMemberId();
        //2. 회원 조회
        MemberGetOneResponse responseDto = new MemberGetOneResponse(memberId,memberAuthInfo.getMemberEmail());

        return responseDto;
    }
    /**
     * 다 건 조회
     */
    @Transactional(readOnly = true)
    public MemberListInquiryResponseDto memberList() {
        List<Member> foundMemberList = memberRepository.findAllByIsDeletedFalse();
        int size = foundMemberList.size();

        List<MemberListInquiryResponseDto.MemberList> memberList = new ArrayList<>();

        for(Member member : foundMemberList) {
           Long foundId = member.getId();
           String foundName = member.getName();

           MemberListInquiryResponseDto.MemberList memberResponseDto = new MemberListInquiryResponseDto.MemberList(foundId,foundName);
           memberList.add(memberResponseDto);
        }

        MemberListInquiryResponseDto responseDto = new MemberListInquiryResponseDto(size,memberList);

        return responseDto;
    }

    /**
     * 멤버 수정
     */
    @Transactional
    public MemberUpdateResponseDto memberUpdate(Long id, MemberUpdateRequestDto memberUpdateRequestDto) {

        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IllegalArgumentException("없는 멤버 입니다."));

        String newName = memberUpdateRequestDto.getName();

        foundMember.updateMemberName(newName);

        return new MemberUpdateResponseDto(foundMember.getId());
    }

    /**
     * 멤버 삭제
     */
    @Transactional
    public void memberDelete(String authorization) {

        MemberAuthInfo memberAuthInfo = jwtService.vaildateToken(authorization);

        Member foundMember = memberRepository.findByIdAndIsDeletedFalse(memberAuthInfo.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("없는 유저입니다."));

        foundMember.softDeleted();
    }
    //추가
}
