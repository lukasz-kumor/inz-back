package com.example.demo.model.adminMsg;

import com.example.demo.model.services.MailSenderService;
import com.example.demo.model.user.ActivateEmailRequest;
import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AdminMsgController {

    @Autowired
    private AdminMsgRepository adminMsgRepository;
    @Autowired
    public MailSenderService mailSenderService;
    @Autowired
    public _UserRepository userRepostiroy;

    @PostMapping(value = "/msg")
    public ResponseEntity<?> postMessage(@RequestBody AdminMsgRequest msgRequest) {

        _User _user = userRepostiroy.findById(msgRequest.getId());
        if(adminMsgRepository.findAllBy_user_Id(msgRequest.getId()).size()>2) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);

        AdminMsgDTO amd = new AdminMsgDTO(msgRequest.getMsg(),_user);
        adminMsgRepository.save(amd);


        return new ResponseEntity<>(msgRequest, HttpStatus.OK);
    }

    @GetMapping(value = "/getmsg")
    public ResponseEntity<List<AdminMsgResponse>> getMessages() {

        List<AdminMsgDTO> amr = adminMsgRepository.findAll();
        if(amr.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<AdminMsgResponse> response = generateMsgResponse(amr);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }


//    @PostMapping(value = "/answer")
//    public ResponseEntity<?> answerMessage(@RequestBody AdminMsgRequest msgRequest) {
//
//        _User _user = userRepostiroy.findById(msgRequest.getId());
//        if(adminMsgRepository.findAllBy_user_Id(msgRequest.getId()).size()>2) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
//
//        AdminMsgDTO amd = new AdminMsgDTO(msgRequest.getMsg(),_user);
//        adminMsgRepository.save(amd);
//
//
//        return new ResponseEntity<>(msgRequest, HttpStatus.OK);
//    }

    private List<AdminMsgResponse> generateMsgResponse(List<AdminMsgDTO> AMD){
        List<AdminMsgResponse> response = new ArrayList<>();

        for(AdminMsgDTO amd : AMD){
            response.add(new AdminMsgResponse(amd));
        }
        return response;
    }

}
