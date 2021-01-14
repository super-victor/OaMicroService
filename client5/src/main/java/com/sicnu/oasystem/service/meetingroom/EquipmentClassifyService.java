package com.sicnu.oasystem.service.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;

/**
 * @InterfaceName EquipmentClassifyService
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/16 11:00
 * @Version v1.0
 */

public interface EquipmentClassifyService {

    /**
     * @MethodName addEquipmentClassify
     * @param classifyName 设备名称
     * @Description 添加设备分类
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage addEquipmentClassify(String classifyName);

    /**
     * @MethodName updateEquipmentClassify
     * @param classifyId 设备Id
     * @param newClassifyName 新的设备名称
     * @Description 修改设备分类
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage updateEquipmentClassify(Integer classifyId, String newClassifyName);

    /**
     * @MethodName deleteEquipmentClassify
     * @param ClassifyId 设备Id
     * @Description 删除设备分类
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage deleteEquipmentClassify(Integer ClassifyId);

    /**
     * @MethodName getAllEquipmentClassify
     * @param
     * @Description 获取所有设备分类
     * @Author pan
     * @Return com.sicnu.oasystem.json.BackFrontMessage
     * @LastChangeDate 2020/11/16
     */
    BackFrontMessage getAllEquipmentClassify();

}
