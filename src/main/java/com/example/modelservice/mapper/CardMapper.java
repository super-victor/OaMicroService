package com.example.modelservice.mapper;

import com.example.modelservice.pojo.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName CardMapper
 * @Description 名片mapper
 * @Author Waynejwei
 * @LastChangeDate 2021/1/16 15:42
 * @Version v1.0
 */

@Mapper
public interface CardMapper {

    List<Card> findCardByEmployeeId(int employeeId);
}