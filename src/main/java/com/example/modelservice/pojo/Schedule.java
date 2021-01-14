package com.example.modelservice.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Schedule
 * @Description 日程实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:14
 * @Version v1.0
 */


@Data
@AllArgsConstructor
public class Schedule {
    @JsonIgnore
    @NotNull(message = "日程id不能为空",groups = {Update.class})
    private Integer scheduleId;
    private Integer isCompany;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "开始时间不能为空",groups = {Company.class, Self.class})
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "结束时间不能为空",groups = {Company.class, Self.class})
    private Date endTime;
    @NotNull(message = "领导人不能为空",groups = {Company.class})
    private Integer leader;
    @NotBlank(message = "日程地点不能为空",groups = {Company.class, Self.class})
    private String location;
    @NotBlank(message = "日程内容不能为空", groups = {Company.class, Self.class})
    private String content;
    private String remark;
    private Integer type;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    @JsonIgnore
    private static final Map<Integer,String> typeList = new HashMap<>(){
        {
            put(1,"聚餐");
            put(2,"会议");
            put(3,"合同签署");
            put(4,"项目");
            put(5,"采购");
            put(6,"资金流转");
            put(7,"出差");
            put(8,"其它");
        }
    };

    private List<Integer> joiner;

    public Schedule(){}

    public interface Company{
    }

    public interface Self{
    }

    public interface Update{
    }

}
