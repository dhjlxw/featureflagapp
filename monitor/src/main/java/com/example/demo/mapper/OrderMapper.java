package com.example.demo.mapper;

import com.example.demo.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("SELECT * FROM `order`")
    List<Order> findAll();

    @Select("SELECT * FROM `order` WHERE id = #{id}")
    Order findById(@Param("id") Integer id);

    @Insert("INSERT INTO `order`(customer_id, product_id, quantity, order_date) " +
            "VALUES(#{customerId}, #{productId}, #{quantity}, #{orderDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Order order);

    @Update("UPDATE `order` SET customer_id=#{customerId}, product_id=#{productId}, " +
            "quantity=#{quantity}, order_date=#{orderDate} WHERE id=#{id}")
    int update(Order order);

    @Delete("DELETE FROM `order` WHERE id=#{id}")
    int delete(@Param("id") Integer id);
}
