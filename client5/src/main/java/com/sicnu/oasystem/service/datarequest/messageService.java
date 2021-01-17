package com.sicnu.oasystem.service.datarequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName messageService
 * @Description
 * @Author pan
 * @LastChangeDate 2021/1/17 9:55
 * @Version v1.0
 */
@Service
@FeignClient("CLIENT-TLY")
public interface messageService {
    @PostMapping("/messagend")
    public int send(@RequestParam("employeeId") int employeeId, @RequestParam("type") int type,
                    @RequestParam("title") String title, @RequestParam("content") String content);
}
