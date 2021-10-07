package project.shop.repository.item;

import project.shop.domain.item.Item;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Long save(Item item);

    Optional<Item> findById(Long id);

    Optional<Item> findByName(String name);

    List<Item> findAll();

}
