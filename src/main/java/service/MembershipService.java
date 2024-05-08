package service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Membership;
import repository.MembershipRepository;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    public Optional<Membership> findByEmail(String email) {
        return membershipRepository.findByEmail(email);
    }

    public Membership saveMembership(Membership membership) {
        return membershipRepository.save(membership);
    }
}