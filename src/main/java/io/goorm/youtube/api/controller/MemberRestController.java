package io.goorm.youtube.api.controller;

import io.goorm.youtube.service.MemberService;
import io.goorm.youtube.vo.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class MemberRestController {

    private final MemberService memberService;

    @Autowired
    public MemberRestController(MemberService memberService) { this.memberService = memberService; }

    //회원가입
    @GetMapping("/{memberId}/duplicate")
    public ResponseEntity<?> checkDuplicateId(@PathVariable("memberId") String memberId) {

        Map<String,String> response = new HashMap<>();

        // 아이디 중복 검사
        if (memberService.existsById(memberId)) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(response); // 중복인 경우 다시 회원가입 폼으로
        }

        return ResponseEntity.ok(response);
    }
}
