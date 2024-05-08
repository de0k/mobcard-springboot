package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import model.Membership;
import service.MembershipService;

@RestController
public class MembershipController {

    private static final Logger logger = LoggerFactory.getLogger(MembershipController.class);

    @Autowired
    private MembershipService membershipService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid Membership membership) {
        logger.info("Signup attempt for email {}", membership.getEmail());

        try {
            if (membershipService.findByEmail(membership.getEmail()).isPresent()) {
                logger.warn("Attempted signup with existing email: {}", membership.getEmail());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 이메일입니다. 다시 입력해주세요.");
            }

            membershipService.saveMembership(membership);
            logger.info("Signup successful for email {}", membership.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
        } catch (Exception e) {
            logger.error("Signup error for email {}: {}", membership.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류로 회원가입 실패");
        }
    }
}
