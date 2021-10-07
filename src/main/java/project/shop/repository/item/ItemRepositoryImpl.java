package project.shop.repository.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.shop.domain.item.Item;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final EntityManager em;

    @Override
    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    @Override
    public Optional<Item> findByName(String name) {
        Item item = em.createQuery("select i from Item i where i.name =: name", Item.class)
                .setParameter("name", name)
                .getSingleResult();
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
