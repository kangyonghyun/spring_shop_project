package project.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.shop.domain.Address;
import project.shop.domain.Member;
import project.shop.domain.item.Book;
import project.shop.domain.item.Item;
import project.shop.domain.item.ItemType;

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
            Member member = new Member("kyh826", "1234", "test", address1);
            Item item = Item.createItem("wow", 1000, 200, true, ItemType.ETC, null, null);
            em.persist(member);
            em.persist(item);
        }

        public void init2() {
            Address address2 = new Address("BUSAN", "333", "444");
            Member member = new Member("hmoon826", "1234", "test", address2);
            Item item = Item.createItem("spring", 2000, 50, true, ItemType.ELECTRONIC, null, null);
            em.persist(member);
            em.persist(item);
        }

    }

}
