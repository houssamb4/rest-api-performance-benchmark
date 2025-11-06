package ma.variante.varianteB.controller;

import ma.variante.varianteB.model.Item;
import ma.variante.varianteB.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getItems(@RequestParam int page, @RequestParam int size) {
        return itemService.getAllItems(page, size);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping(params = "categoryId")
    public List<Item> getItemsByCategory(@RequestParam Long categoryId) {
        return itemService.getItemsByCategory(categoryId);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public boolean deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
}