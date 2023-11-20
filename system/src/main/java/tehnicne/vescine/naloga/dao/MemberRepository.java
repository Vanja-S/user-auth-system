package tehnicne.vescine.naloga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tehnicne.vescine.naloga.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
