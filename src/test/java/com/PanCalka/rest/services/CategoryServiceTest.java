package com.PanCalka.rest.services;

import com.PanCalka.rest.api.v1.model.CategoryDTO;
import com.PanCalka.rest.api.v1.model.mapper.CategoryMapper;
import com.PanCalka.rest.domain.Category;
import com.PanCalka.rest.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    static final String NAME = "test";
    static final long ID = 1L;
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void shouldGetAllCategories() {
        // given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> allCategories = categoryService.getAllCategories();

        //then
        assertEquals(3, allCategories.size());
    }

    @Test
    public void shouldGetCategoryByName() {
        // given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(NAME, categoryDTO.getName());
        assertTrue(categoryDTO.getId().equals(ID));
    }
}
