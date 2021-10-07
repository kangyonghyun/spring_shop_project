package project.shop.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.shop.domain.Category;
import project.shop.domain.CategoryItem;
import project.shop.domain.UploadFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private boolean open;

    @Embedded
    private UploadFile attachFile;
    private String imageFiles;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    public Item(String name, int price, int stockQuantity, boolean open, ItemType itemType, UploadFile attachFile, String imageFiles) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.open = open;
        this.itemType = itemType;
        this.attachFile = attachFile;
        this.imageFiles = imageFiles;
    }

    public static Item createItem(String name, int price, int stockQuantity, boolean open,
                                  ItemType itemType, UploadFile attachFile, String imageFiles) {
        return new Item(name, price, stockQuantity, open, itemType, attachFile, imageFiles);
    }

}
