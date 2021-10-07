package project.shop.repository.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.shop.web.dto.MemberListForm;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryQuery {

    private final EntityManager em;

    public List<MemberListForm> findAll() {
        return em.createQuery("select new project.shop.web.dto.MemberListForm(m.id, m.loginId, m.name, m.address) " +
                "from Member m", MemberListForm.class)
                .getResultList();
    }

}
