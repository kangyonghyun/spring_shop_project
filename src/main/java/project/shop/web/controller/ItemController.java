package project.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.shop.domain.UploadFile;
import project.shop.domain.item.Item;
import project.shop.file.FileStore;
import project.shop.service.item.ItemService;
import project.shop.web.dto.ItemSaveForm;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final FileStore fileStore;

    @GetMapping("items/add")
    public String createForm(Model model) {
        model.addAttribute("itemSaveForm", new ItemSaveForm());
        return "items/createItem";
    }

    @PostMapping("items/add")
    public String create(@Valid @ModelAttribute ItemSaveForm itemSaveForm, BindingResult bindingResult) throws IOException {
        UploadFile attachFile = fileStore.storeFile(itemSaveForm.getAttachFile());
        List<UploadFile> storeFiles = fileStore.storeFiles(itemSaveForm.getImageFiles());

        Optional<String> imagePath = Optional.empty();
        if (!storeFiles.isEmpty()) {
            imagePath = Optional.ofNullable(fileStore.getPath());
        }

        Item item = Item.createItem(itemSaveForm.getName(), itemSaveForm.getPrice(), itemSaveForm.getStockQuantity(),
                itemSaveForm.getOpen(), itemSaveForm.getItemType(), attachFile, imagePath.orElse(null));

        itemService.saveItem(item);

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items")
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
}
