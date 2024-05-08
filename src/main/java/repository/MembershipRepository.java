package repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import model.Membership;

public interface MembershipRepository extends JpaRepository<Membership, String> {
    Optional<Membership> findByEmail(String email);
}
