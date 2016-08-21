package com.koton.test.controller;


import com.koton.controller.CartController;
import com.koton.domain.ProductEntity;
import com.koton.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by MacbookPro on 21/08/16.
 */

public class TestCartController {

    @InjectMocks
    private CartController cartController;

    @Mock
    private ProductService service;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);


        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();

        cartController.init();
    }

    @Test
    public void testGet() throws Exception{

        mockMvc.perform(get("/cart/get").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void testAddCartOperations() throws Exception{

        ProductEntity p = new ProductEntity(1L,"","","",10d,null);
        when(service.findById(1L)).thenReturn(p);


        mockMvc.perform(get("/cart/updateProductAmountInCart/1/1/false").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        mockMvc.perform(get("/cart/updateProductAmountInCart/1/1/false").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(2)));

        mockMvc.perform(get("/cart/updateProductAmountInCart/1/3/true").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(3)));


    }


    /*
     *           ***  SUBTRACTION TEST ***
     *
     *   SUBTRACT BY 1 TILL AMOUNT FOR "PRODUCT 1" BECOMES 0
     *   AND EXPECT PRODUCT TO BE REMOVED FROM CART
     */
    @Test
    public void testSubtractionOperations() throws Exception{
        ProductEntity p = new ProductEntity(1L,"","","",10d,null);
        when(service.findById(1L)).thenReturn(p);



        mockMvc.perform(get("/cart/updateProductAmountInCart/1/3/true").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(3)));

        mockMvc.perform(get("/cart/subtractProductFromCart/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(2)));

        mockMvc.perform(get("/cart/subtractProductFromCart/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(1)));


        //PRODUCT EXPECTED TO BE REMOVED
        mockMvc.perform(get("/cart/subtractProductFromCart/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testEmtyCart() throws Exception{
        ProductEntity p = new ProductEntity(1L,"","","",10d,null);
        when(service.findById(1L)).thenReturn(p);

        mockMvc.perform(get("/cart/updateProductAmountInCart/1/3/true").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(3)));

        //EXPECT EMPTY CART
        mockMvc.perform(delete("/cart/emtyCart").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

}
