package service;

import model.Item;
import model.Category;
import repository.ItemRepository;
import repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Item> getAllItems(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return itemRepository.findAll(pageable).getContent();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> getItemsByCategory(Long categoryId) {
        return itemRepository.findByCategoryId(categoryId);
    }

    public Item createItem(Item item) {
        if (item.getCategory() != null && item.getCategory().getId() != null) {
            Category managedCategory = categoryRepository.findById(item.getCategory().getId()).orElse(null);
            if (managedCategory != null) {
                item.setCategory(managedCategory);
            }
        }
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item item) {
        if (itemRepository.existsById(id)) {
            if (item.getCategory() != null && item.getCategory().getId() != null) {
                Category managedCategory = categoryRepository.findById(item.getCategory().getId()).orElse(null);
                if (managedCategory != null) {
                    item.setCategory(managedCategory);
                }
            }
            item.setId(id);
            return itemRepository.save(item);
        }
        return null;
    }

    public boolean deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}