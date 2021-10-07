package project.shop.repository.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import project.shop.domain.item.Item;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ItemRepositoryImplTest {

    @Autowired
    ItemRepository itemRepository;
    Item item;
    Long itemId;

    @BeforeEach
    void init() {
//        item = Item.createItem("item", 3000, 10);
        itemId = itemRepository.save(item);
    }

    @Test
    void 아이템_저장_찾기() {
        Item findItem = itemRepository.findById(itemId).orElse(null);
        assertThat(item).isEqualTo(findItem);
    }

    @Test
    void 아이템_이름_찾기() {
        Item findItem = itemRepository.findByName("item").orElseThrow();
        assertThat(findItem.getName()).isEqualTo("item");
    }

    @Test
    void findAll() {
        List<Item> items = itemRepository.findAll();
        assertThat(items.size()).isEqualTo(1);
    }
}