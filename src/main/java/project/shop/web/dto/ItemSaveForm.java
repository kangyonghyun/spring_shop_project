package project.shop.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import project.shop.domain.item.ItemType;

import java.util.List;

@Getter @Setter
public class ItemSaveForm {

    private String name;
    private int price;
    private int stockQuantity;

    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;

    private Boolean open;
    private ItemType itemType;
    private String deliveryCode;

}
