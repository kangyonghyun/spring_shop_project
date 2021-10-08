package project.shop.web.dto;

import lombok.Data;
import project.shop.domain.UploadFile;
import project.shop.domain.item.ItemType;

@Data
public class ItemViewForm {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    private UploadFile attachFile;
//    private List<MultipartFile> imageFiles;

    private Boolean open;
    private ItemType itemType;

    public ItemViewForm(Long id, String name, int price, int stockQuantity, Boolean open, ItemType itemType, UploadFile attachFile) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.open = open;
        this.itemType = itemType;
        this.attachFile = attachFile;
    }

    public static ItemViewForm createForm(Long id, String name, int price, int stockQuantity, boolean open, ItemType itemType, UploadFile attachFile) {
        return new ItemViewForm(id, name, price, stockQuantity, open, itemType, attachFile);
    }
}
