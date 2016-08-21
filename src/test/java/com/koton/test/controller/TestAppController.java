package com.koton.test.controller;


import com.koton.controller.AppController;
import com.koton.domain.CategoryEntity;
import com.koton.domain.ProductEntity;
import com.koton.service.CategoryService;
import com.koton.service.ProductService;
import com.koton.utils.BaseEntityList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by MacbookPro on 21/08/16.
 */

public class TestAppController {

    @Mock
    private CategoryService categoryService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private AppController appController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(appController).build();
    }

    @Test
    public void testWellcome() throws Exception{
        BaseEntityList<CategoryEntity> list = new BaseEntityList<>();
        list.add(new CategoryEntity(1L,"hi1"));
        list.add(new CategoryEntity(2L,"hi2"));

        when(categoryService.findAll()).thenReturn(list);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("categories",hasSize(2)));

    }

    @Test
    public void testOnCategorySelect() throws Exception{

        mockMvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("selectedCategoryId",is(1L)));

    }

}
