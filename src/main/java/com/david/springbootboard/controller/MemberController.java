package com.david.springbootboard.controller;

import com.david.springbootboard.entity.Member;
import com.david.springbootboard.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@AllArgsConstructor
public class MemberController {
    MemberService memberService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
        Member member = memberService.login(id, password);
        // 로그인 성공
        if (member != null ){
            model.addAttribute("Member", member);
            return "redirect:/board/list";
        }
        else {
            // 로그인 실패
            model.addAttribute("loginResult", "로그인에 실패하셨습니다");
            return "login";
        }
    }
}
