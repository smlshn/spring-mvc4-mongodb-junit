package com.koton.controller;

import com.koton.domain.ProductEntity;
import com.koton.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * Created by MacbookPro on 20/08/16.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, ModelMap model) {

		model.addAttribute("product", productService.findById(id));
		return "product-detail";

	}

	public void setService(ProductService service) {
		this.productService = service;
	}
}