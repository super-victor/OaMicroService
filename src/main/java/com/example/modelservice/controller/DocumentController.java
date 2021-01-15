package com.example.modelservice.controller;

import com.example.modelservice.mapper.DocumentMapper;
import com.example.modelservice.pojo.SendFile;
import com.example.modelservice.pojo.limit.EmployeeLimitA;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DocumentController
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/14 18:54
 * @Version v1.0
 */

@RestController
public class DocumentController {

    @Resource
    DocumentMapper documentMapper;

    @PostMapping("/insertSendFile")
    public int insertSendFile(@RequestBody SendFile sendFile){
        return documentMapper.insertSendFile(sendFile);
    }

    @GetMapping("/findEmptySendFileByEmployeeId")
    public SendFile findEmptySendFileByEmployeeId(@RequestParam int employeeId){
        return documentMapper.findEmptySendFileByEmployeeId(employeeId);
    }

    @GetMapping("/findEmployeeIdsInDocumentAcess")
    public List<Integer> findEmployeeIdsInDocumentAcess(@RequestParam int sendfileId){
        return documentMapper.findEmployeeIdsInDocumentAcess(sendfileId);
    }

    @GetMapping("/gethaveCheckAbilityEmployList")
    public List<EmployeeLimitA> gethaveCheckAbilityEmployList(){
        return documentMapper.gethaveCheckAbilityEmployList();
    }

    @GetMapping("/ishaveCheckAbility")
    public int ishaveCheckAbility(@RequestParam int employeeId){
        return documentMapper.ishaveCheckAbility(employeeId);
    }

    @PostMapping("/insertDocumentAcess")
    public int insertDocumentAcess(@RequestParam int sendfileId,@RequestParam int employeeId){
        return documentMapper.insertDocumentAcess(sendfileId, employeeId);
    }

    @DeleteMapping("/deleteDocumentAcess")
    public int deleteDocumentAcess(@RequestParam int sendfileId){
        return documentMapper.deleteDocumentAcess(sendfileId);
    }

    @DeleteMapping("/deleteDocumentAcessBySendfileIdAndEmployeeId")
    public int deleteDocumentAcessBySendfileIdAndEmployeeId(@RequestParam int sendfileId,@RequestParam int employeeId){
        return documentMapper.deleteDocumentAcessBySendfileIdAndEmployeeId(sendfileId, employeeId);
    }

    @PutMapping("/updateStatusAndCommentInSendFile")
    public int updateStatusAndCommentInSendFile(@RequestParam int sendfileId,@RequestParam int status, @RequestParam String comment){
        return documentMapper.updateStatusAndCommentInSendFile(sendfileId,status,comment);
    }

    @PutMapping("/updateSendFileBySendFile")
    public int updateSendFileBySendFile(@RequestBody SendFile sendFile){
        return documentMapper.updateSendFileBySendFile(sendFile);
    }

    @GetMapping("/findSendFileNeedCheck")
    public List<SendFile> findSendFileNeedCheck(@RequestParam int censorId){
        return documentMapper.findSendFileNeedCheck(censorId);
    }

    @GetMapping("findAnnexUrlsBySendfileId")
    public List<String> findAnnexUrlsBySendfileId(@RequestParam Integer sendfileId){
        return documentMapper.findAnnexUrlsBySendfileId(sendfileId);
    }

    @DeleteMapping("/deleteDocumentAnnexByannexUrl")
    public int deleteDocumentAnnexByannexUrl(@RequestParam String annexUrl){
        return documentMapper.deleteDocumentAnnexByannexUrl(annexUrl);
    }

    @PostMapping("/insertDocumentAnnex")
    public int insertDocumentAnnex(@RequestParam int sendfileId,@RequestParam String annexUrl){
        return documentMapper.insertDocumentAnnex(sendfileId, annexUrl);
    }

    @GetMapping("/findSendFileByEmployeeIdAndSendfileId")
    public SendFile findSendFileByEmployeeIdAndSendfileId(@RequestParam Integer employeeId,@RequestParam Integer sendfileId){
        return documentMapper.findSendFileByEmployeeIdAndSendfileId(employeeId, sendfileId);
    }

    @GetMapping("/findUnReviewSendFile")
    public List<SendFile> findUnReviewSendFile(){
        return documentMapper.findUnReviewSendFile();
    }

    @PutMapping("/updateStatusInSendFile")
    public int updateStatusInSendFile(@RequestParam int sendfileId,@RequestParam int status){
        return documentMapper.updateStatusInSendFile(sendfileId, status);
    }

    @PutMapping("/checkedReceiveFile")
    public int checkedReceiveFile(int receivefileId){
        return documentMapper.checkedReceiveFile(receivefileId);
    }


    @GetMapping("/findSendFileBySendfileId")
    public SendFile findSendFileBySendfileId(@RequestParam int sendfileId){
        return documentMapper.findSendFileBySendfileId(sendfileId);
    }

    @GetMapping("/findSendFileByEmployeeIdAndStatus")
    public List<SendFile> findSendFileByEmployeeIdAndStatus(@RequestParam int employeeId,@RequestParam int status){
        return documentMapper.findSendFileByEmployeeIdAndStatus(employeeId, status);
    }

    @GetMapping("/findAccessEmployeesBySendfileId")
    public List<EmployeeLimitA> findAccessEmployeesBySendfileId(@RequestParam int sendfileId){
        return documentMapper.findAccessEmployeesBySendfileId(sendfileId);
    }

    @DeleteMapping("/deleteSendFile")
    public int deleteSendFile(@RequestParam int sendfileId){
        return documentMapper.deleteSendFile(sendfileId);
    }

    @GetMapping("/findAllPublishSendFile")
    public List<SendFile> findAllPublishSendFile(){
        return documentMapper.findAllPublishSendFile();
    }

    @GetMapping("/findSelfPublishSendFile")
    public List<SendFile> findSelfPublishSendFile(@RequestParam int senderId){
        return documentMapper.findSelfPublishSendFile(senderId);
    }


}
