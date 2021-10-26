package project.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import project.shop.domain.UploadFile;
import project.shop.domain.item.Item;
import project.shop.file.FileStore;
import project.shop.service.item.ItemService;
import project.shop.web.dto.ItemViewForm;
import project.shop.web.dto.ItemSaveForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final FileStore fileStore;

    @GetMapping("/items/add")
    public String createForm(Model model) {
        model.addAttribute("itemSaveForm", new ItemSaveForm());
        return "items/createItem";
    }

    @PostMapping("/items/add")
    public String create(@Valid @ModelAttribute ItemSaveForm itemSaveForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile attachFile = fileStore.storeFile(itemSaveForm.getAttachFile());
        List<UploadFile> storeFiles = fileStore.storeFiles(itemSaveForm.getImageFiles());

        Optional<String> imagePath = Optional.empty();
        if (!storeFiles.isEmpty()) {
            imagePath = Optional.ofNullable(fileStore.getPath());
        }

        Item item = Item.createItem(itemSaveForm.getName(), itemSaveForm.getPrice(), itemSaveForm.getStockQuantity(),
                itemSaveForm.getOpen(), itemSaveForm.getItemType(), attachFile, imagePath.orElse(null));

        itemService.saveItem(item);

        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addFlashAttribute("files", storeFiles);

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String item(@PathVariable Long id, Model model, HttpServletRequest request) {
        Item item = itemService.findOne(id).orElseThrow();
        ItemViewForm itemViewForm = ItemViewForm.createForm(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity(), item.isOpen(), item.getItemType(), item.getAttachFile());
        model.addAttribute("item", itemViewForm);

        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            List<UploadFile> files = (List<UploadFile>) inputFlashMap.get("files");
            model.addAttribute("files", files);
        }
        return "items/itemView";
    }

    @GetMapping("/items/{id}/edit")
    public String item(@PathVariable Long id, Model model) {
        Item item = itemService.findOne(id).orElseThrow();
        ItemViewForm itemViewForm = ItemViewForm.createForm(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity(), item.isOpen(), item.getItemType(), item.getAttachFile());
        model.addAttribute("item", itemViewForm);
        return "items/updateItem";
    }

    @PostMapping("/items/{id}/edit")
    public String item(@PathVariable Long id, ItemViewForm itemViewForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        itemService.updateItem(id, itemViewForm.getName(), itemViewForm.getPrice(), itemViewForm.getStockQuantity(), itemViewForm.getItemType(), itemViewForm.getAttachFile());
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String items(Model model) {
        List<Item> items = itemService.findItems();
        List<ItemViewForm> itemViewForms = new ArrayList<>();
        for (Item item : items) {
            ItemViewForm form = ItemViewForm.createForm(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity(), item.isOpen(), item.getItemType(), item.getAttachFile());
            itemViewForms.add(form);
        }
        model.addAttribute("items", itemViewForms);
        return "items/itemList";
    }
}
