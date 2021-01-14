package com.sicnu.oasystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName DataSeeAbleA
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/12/14 18:16
 * @Version v1.0
 */

@Data
@AllArgsConstructor
public class DataSeeAbleA {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recordDate;
    private Integer num;
}
