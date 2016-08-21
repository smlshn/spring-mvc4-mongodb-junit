package com.koton.controller;

import com.koton.domain.CategoryEntity;
import com.koton.domain.ProductEntity;
import com.koton.service.CategoryService;
import com.koton.service.ProductService;
import com.koton.utils.BaseEntityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacbookPro on 20/08/16.
 */
@Controller
public class AppController {

	private BaseEntityList<ProductEntity> cart;
	private BaseEntityList<CategoryEntity> categories;


	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;


	@PostConstruct
	public void init(){
		categoryService.deleteAll();
		productService.deleteAll();
		cart = new BaseEntityList<>();

		for(int i=1; i<4; i++){
			CategoryEntity c = new CategoryEntity(new Long(i),"Category "+i);
			c = categoryService.create(c);
			List<ProductEntity> products = new ArrayList<>();
			for(int j=1; j<21; j++){
				ProductEntity p = new ProductEntity(Long.valueOf(i+""+j), "Product "+i+""+j, (i)+" x "+(i*j/2), "", j*j*1000d, c);
				products.add(p);
			}
			productService.save(products);
		}

		categories = categoryService.findAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String wellcome(ModelMap model) {

		//added here just for testing purpose
		if(categories==null)
			categories = categoryService.findAll();

		Long selectedCategoryId = categories.get(0).getId();

		model.addAttribute("categories", categories);

		model.addAttribute("products",productService.findByCategoryId(selectedCategoryId));
		model.addAttribute("selectedCategoryId",selectedCategoryId);
		return "index";

	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String onCategorySelect(@PathVariable("id") Long id, ModelMap model) {


		model.addAttribute("categories", categories);
		model.addAttribute("products",productService.findByCategoryId(id));
		model.addAttribute("selectedCategoryId",id);
		return "index";
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}