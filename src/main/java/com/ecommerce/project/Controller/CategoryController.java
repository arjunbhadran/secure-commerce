package com.ecommerce.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // public CategoryController(CategoryService categoryService) {
    //     this.categoryService = categoryService;
    // }

    @GetMapping("/api/public/categories")
    //@RequestMapping(value = "/api/public/categories",method=RequestMethod.GET)//another way for the above
    private ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PostMapping("/api/admin/categories")
    public ResponseEntity<String> postMethodName(@RequestBody Category category) {
        categoryService.createCategory(category);
        return ResponseEntity.ok("Category added Successfully!");
    }
    
    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        try{
        String status=categoryService.deleteCategory(categoryId);
        //return new ResponseEntity<>(status,HttpStatus.OK);
        return ResponseEntity.ok(status);//another way
        }catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> putMethodName(@RequestBody Category category,@PathVariable Long categoryId) {
        try{
            Category savedCategory=categoryService.updateCategory(category,categoryId);
            return ResponseEntity.ok("Category with categoryId: "+savedCategory.getCategoryId());
        }catch(ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
}
