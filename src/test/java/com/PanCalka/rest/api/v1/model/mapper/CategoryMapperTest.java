package com.PanCalka.rest.api.v1.model.mapper;

import com.PanCalka.rest.api.v1.model.CategoryDTO;
import com.PanCalka.rest.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    static final String NAME = "test";
    static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void shouldConvertCategoryToCategoryDTO() {
        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(ID),categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}