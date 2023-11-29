package tehnicne.vescine.naloga.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tehnicne.vescine.naloga.dao.MemberRepository;
import tehnicne.vescine.naloga.entity.Member;
import tehnicne.vescine.naloga.entity.Role;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository memberRepository;
    private EntityManager entityManager;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, EntityManager entityManager) {
        this.memberRepository = memberRepository;
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
        addRole(member.getUsername());
    }

    @Transactional
    public void addRole(String username) {
        Role role = new Role();
        role.setUsername(username);
        role.setRole("ROLE_EMPLOYEE");
        entityManager.merge(role);
    }

    @Override
    @Transactional
    public void deleteByUsername(String username) {
        Optional<Member> member = memberRepository.findById(username);
        if (member.isPresent()) {
            memberRepository.deleteById(username);
        }
    }
}
