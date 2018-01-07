package com.example.demo.model.adminMsg;

import com.example.demo.model.services.MailSenderService;
import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminMsgController {

    @Autowired
    private AdminMsgRepository adminMsgRepository;
    @Autowired
    public MailSenderService mailSenderService;
    @Autowired
    public _UserRepository userRepository;

    @PostMapping(value = "/msg")
    public ResponseEntity<?> postMessage(@RequestBody AdminMsgRequest msgRequest) {

        _User _user = userRepository.findById(msgRequest.getId());
        if(adminMsgRepository.findBy_user_Id(msgRequest.getId())!=null) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        if(msgRequest.getMsg().length()>100) return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        AdminMsgDAO amd = new AdminMsgDAO(msgRequest.getMsg(),_user);
        adminMsgRepository.save(amd);

        return new ResponseEntity<>(msgRequest, HttpStatus.OK);
    }

    @GetMapping(value = "/getmsg")
    public ResponseEntity<List<AdminMsgResponse>> getMessages() {

        List<AdminMsgDAO> amr = adminMsgRepository.findAll();
        if(amr.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<AdminMsgResponse> response = generateMsgResponse(amr);

        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PostMapping(value = "/answer")
    public ResponseEntity<?> answerMessage(@RequestBody AdminAnswer answer) {

        _User user_messager = userRepository.findByEmail(answer.getEmail());

        String from = "basketblast1234@gmail.com";
        String to = answer.getEmail();
        String subject = "Odpowiedź serwisu BasketBlast";
        String body = "Twoja wiadomość: " + answer.getMsgTo() + "\nOdpowiedź: " + answer.getMsgFrom();
        mailSenderService.sendEmail(from,to,subject,body);
        adminMsgRepository.delete(adminMsgRepository.findBy_user_Id(user_messager.getId()));

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @PostMapping(value = "/delete/msg")
    public ResponseEntity<?> deleteMessage(@RequestBody AdminAnswer answer) {

        _User user_message = userRepository.findByEmail(answer.getEmail());
        adminMsgRepository.delete(adminMsgRepository.findBy_user_Id(user_message.getId()));

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }


    private List<AdminMsgResponse> generateMsgResponse(List<AdminMsgDAO> AMD){
        List<AdminMsgResponse> response = new ArrayList<>();

        for(AdminMsgDAO amd : AMD){
            response.add(new AdminMsgResponse(amd));
        }
        return response;
    }

}
