package com.example.scheduleservice.service;

import com.example.scheduleservice.json.BackFrontMessage;

/**
 * @InterfaceName CardService
 * @Description 名片服务
 * @Author Waynejwei
 * @LastChangeDate 2021/1/16 15:50
 * @Version v1.0
 */

public interface CardService {

    BackFrontMessage findCardByEmployeeId();
}