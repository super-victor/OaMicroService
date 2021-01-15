package com.example.modelservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:32
 * @Description:设备分类实体类
 */
@Data
public class EquipmentClassify {
    private Integer equipmentClassifyId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
