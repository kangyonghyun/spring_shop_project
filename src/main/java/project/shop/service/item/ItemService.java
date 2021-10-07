package project.shop.service.item;

import project.shop.domain.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    public void saveItem(Item item);

    public Optional<Item> findOne(Long itemId);

    public Optional<Item> findOneByName(String name);

    public List<Item> findItems();
}
