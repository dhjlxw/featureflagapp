package com.example.demo.mapper;

import com.example.demo.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM product")
    List<Product> findAll();

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(@Param("id") Integer id);

    @Insert("INSERT INTO product(name, title, price) VALUES(#{name}, #{title}, #{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    @Update("UPDATE product SET name=#{name}, title=#{title}, price=#{price} WHERE id=#{id}")
    int update(Product product);

    @Delete("DELETE FROM product WHERE id=#{id}")
    int delete(@Param("id") Integer id);
}
