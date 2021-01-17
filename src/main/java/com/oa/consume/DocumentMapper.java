package com.oa.consume;

import com.oa.pojo.SendFile;
import com.oa.pojo.limit.EmployeeLimitA;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DocumentMapper
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/14 19:24
 * @Version v1.0
 */

@FeignClient(name = "model-service")
@Service
public interface DocumentMapper {

    @PostMapping("/insertSendFile")
    int insertSendFile(@RequestBody SendFile sendFile);

    @GetMapping("/findEmptySendFileByEmployeeId")
    SendFile findEmptySendFileByEmployeeId(@RequestParam int employeeId);

    @GetMapping("/findEmployeeIdsInDocumentAcess")
    List<Integer> findEmployeeIdsInDocumentAcess(@RequestParam int sendfileId);

    @GetMapping("/gethaveCheckAbilityEmployList")
    List<EmployeeLimitA> gethaveCheckAbilityEmployList();

    @GetMapping("/ishaveCheckAbility")
    int ishaveCheckAbility(@RequestParam int employeeId);

    @PostMapping("/insertDocumentAcess")
    int insertDocumentAcess(@RequestParam int sendfileId,@RequestParam int employeeId);

    @DeleteMapping("/deleteDocumentAcess")
    int deleteDocumentAcess(@RequestParam int sendfileId);

    @DeleteMapping("/deleteDocumentAcessBySendfileIdAndEmployeeId")
    int deleteDocumentAcessBySendfileIdAndEmployeeId(@RequestParam int sendfileId,@RequestParam int employeeId);

    @PutMapping("/updateStatusAndCommentInSendFile")
    int updateStatusAndCommentInSendFile(@RequestParam int sendfileId,@RequestParam int status, @RequestParam String comment);

    @PutMapping("/updateSendFileBySendFile")
    int updateSendFileBySendFile(@RequestBody SendFile sendFile);

    @GetMapping("/findSendFileNeedCheck")
    List<SendFile> findSendFileNeedCheck(@RequestParam int censorId);

    @GetMapping("findAnnexUrlsBySendfileId")
    List<String> findAnnexUrlsBySendfileId(@RequestParam Integer sendfileId);

    @DeleteMapping("/deleteDocumentAnnexByannexUrl")
    int deleteDocumentAnnexByannexUrl(@RequestParam String annexUrl);

    @PostMapping("/insertDocumentAnnex")
    int insertDocumentAnnex(@RequestParam int sendfileId,@RequestParam String annexUrl);

    @GetMapping("/findSendFileByEmployeeIdAndSendfileId")
    SendFile findSendFileByEmployeeIdAndSendfileId(@RequestParam Integer employeeId,@RequestParam Integer sendfileId);

    @GetMapping("/findUnReviewSendFile")
    List<SendFile> findUnReviewSendFile();

    @PutMapping("/updateStatusInSendFile")
    int updateStatusInSendFile(@RequestParam int sendfileId,@RequestParam int status);

    @PutMapping("/checkedReceiveFile")
    int checkedReceiveFile(int receivefileId);


    @GetMapping("/findSendFileBySendfileId")
    SendFile findSendFileBySendfileId(@RequestParam int sendfileId);

    @GetMapping("/findSendFileByEmployeeIdAndStatus")
    List<SendFile> findSendFileByEmployeeIdAndStatus(@RequestParam int employeeId,@RequestParam int status);

    @GetMapping("/findAccessEmployeesBySendfileId")
    List<EmployeeLimitA> findAccessEmployeesBySendfileId(@RequestParam int sendfileId);

    @DeleteMapping("/deleteSendFile")
    int deleteSendFile(@RequestParam int sendfileId);

    @GetMapping("/findAllPublishSendFile")
    List<SendFile> findAllPublishSendFile();

    @GetMapping("/findSelfPublishSendFile")
    List<SendFile> findSelfPublishSendFile(@RequestParam int senderId);
}
