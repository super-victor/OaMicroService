package com.example.modelservice.mapper;

import com.example.modelservice.pojo.WxInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @InterfaceName WxMapper
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/16 8:45
 * @Version v1.0
 */

@Mapper
public interface WxMapper {

    int insertIntoWxinfo(WxInfo wxInfo);

    WxInfo selectFromWxinfo(String openid);

    int updateWxInfo(WxInfo wxInfo);

    int deleteWxinfo(String openid);
}
