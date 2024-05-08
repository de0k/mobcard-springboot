package controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import model.Membership;
import service.MembershipService;

@RestController
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid Membership membership) {
        Optional<Membership> existingMembership = membershipService.findByEmail(membership.getEmail());
        if (existingMembership.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 이메일입니다. 다시 입력해주세요.");
        }
        membershipService.saveMembership(membership);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }
}
