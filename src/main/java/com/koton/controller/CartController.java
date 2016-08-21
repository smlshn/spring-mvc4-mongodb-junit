package com.koton.controller;

import com.koton.cart.AddToCart;
import com.koton.cart.CartElement;
import com.koton.cart.CartOperationStrategy;
import com.koton.cart.SubtractFromCart;
import com.koton.domain.ProductEntity;
import com.koton.service.ProductService;
import com.koton.utils.BaseEntityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

/**
 * Created by MacbookPro on 20/08/16.
 */
@Controller
@SessionAttributes({"cart"})
@Scope(value ="session")
@RequestMapping(value = "/cart")
public class CartController {

	private BaseEntityList<CartElement> cart;
	private CartOperationStrategy strategy;

	@Autowired
	private ProductService service;

	@PostConstruct
	public void init(){
		cart = new BaseEntityList<>();
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody BaseEntityList<CartElement> getCart(){
		return cart;
	}

	@RequestMapping(value = "/updateProductAmountInCart/{id}/{amount}/{override}", method = RequestMethod.GET)
	public @ResponseBody BaseEntityList<CartElement> updateProductAmountInCart(
			@PathVariable("id") Long productId, @PathVariable("amount") int amount, @PathVariable("override") boolean overrideAmount) {

		strategy = new AddToCart(amount,overrideAmount);
		return strategy.updateList(service.findById(productId),cart);
	}

	@RequestMapping(value = "/subtractProductFromCart/{id}", method = RequestMethod.GET)
	public @ResponseBody BaseEntityList<CartElement> subtractProductFromCart(@PathVariable("id") Long productId) {

		strategy = new SubtractFromCart(1);
		return strategy.updateList(service.findById(productId),cart);
	}

	@RequestMapping(value = "/removeProductFromCart/{id}", method = RequestMethod.GET)
	public @ResponseBody BaseEntityList<CartElement> removeProductFromCart(@PathVariable("id") Long productId) {

		strategy = new SubtractFromCart(true);
		return strategy.updateList(service.findById(productId),cart);
	}

	@RequestMapping(value = "/emtyCart", method = RequestMethod.DELETE)
	public @ResponseBody BaseEntityList<CartElement> emtyCart() {
		cart = new BaseEntityList<>();
		return cart;
	}

}