package project.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.shop.domain.Address;
import project.shop.domain.Member;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.init1();
        initService.init2();
    }

    @Component
    @RequiredArgsConstructor
    @Transactional
    static class InitService {

        private final EntityManager em;

        public void init1() {
            Address address1 = new Address("SEOUL", "111", "222");
            Member member1 = new Member("kyh826", "1234", "test", address1);
            em.persist(member1);
        }

        public void init2() {
            Address address2 = new Address("BUSAN", "333", "444");
            Member member2 = new Member("hmoon826", "1234", "test", address2);
            em.persist(member2);
        }

    }

}
