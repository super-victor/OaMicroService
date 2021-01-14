package com.example.modelservice.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName CArdHolder
 * @Description 名片夹实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:17
 * @Version v1.0
 */

@Data
@AllArgsConstructor
public class CardHolder {
    private Integer cardHolderId;
    //    @ApiModelProperty(required = true)
    @NotBlank(message = "文件夹名字不能为空")
    private String name;
    private Integer ownerId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public CardHolder(){}

}