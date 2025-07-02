package com.ecommerce.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
    //private List<Category> categories = new ArrayList<>();
    private long nextId=0L;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override 
    public List<Category> getAllCategories(){
        //return categories;
        return categoryRepository.findAll();
    }
    @Override
    public void createCategory(Category category){
        // if(category.getCategoryId()==null){
        //     category.setCategoryId(++nextId);
        // }
        //categories.add(category);
        categoryRepository.save(category);
    }
    @Override
    public String deleteCategory(Long cId){
        // for(int i=0;i<categories.size();i++){
        //     Category c=categories.get(i);
        //     if(c.getCategoryId()==cId){
        //         categories.remove(i);
        //         return "Removed category "+String.valueOf(cId);
        //     }
        // }
        // return "Failed to remove category "+String.valueOf(cId);

        //to do it using the stream api
        //Category category=categories.stream().filter(c->c.getCategoryId().equals(cId)).findFirst().get();
        //above one would have issues with invalid categoryIds
        // List<Category > categories=categoryRepository.findAll();
        // Category category=categories.stream().filter(c->c.getCategoryId().equals(cId)).findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found."));
        // if(categories.contains(category)){
        //     //categories.remove(category);
        //     categoryRepository.delete(category);
        //     return "Removed category "+String.valueOf(cId);
        // }
        // return "Failed to remove category "+String.valueOf(cId);
        //above code does not utilise the findById method. below one uses it.
        Optional<Category> category=categoryRepository.findById(cId);
        if(category.isPresent()){
            categoryRepository.deleteById(cId);
            return "Removed category "+String.valueOf(cId);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found for category "+String.valueOf(cId));
        //return "Failed to remove category "+String.valueOf(cId);
    }
    @Override
    public Category updateCategory(Category category, Long cId){
        // List<Category> categories=categoryRepository.findAll();
        Optional<Category> categories=categoryRepository.findById(cId);
        Optional<Category> category2=categories.stream().filter(c->c.getCategoryId().equals(cId)).findFirst();
        if(category2.isPresent()){
            Category existingCategory= category2.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category savedCategory = categoryRepository.save(existingCategory);
            return savedCategory;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource not found.");
        }
    }
}
