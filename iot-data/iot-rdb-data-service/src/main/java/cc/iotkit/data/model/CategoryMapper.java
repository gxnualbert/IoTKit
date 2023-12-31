package cc.iotkit.data.model;

import cc.iotkit.model.product.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper M = Mappers.getMapper(CategoryMapper.class);

    Category toDto(TbCategory vo);

    TbCategory toVo(Category dto);
}
