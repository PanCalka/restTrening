package com.PanCalka.rest.api.v1.model.mapper;

import com.PanCalka.rest.api.v1.model.CategoryDTO;
import com.PanCalka.rest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
