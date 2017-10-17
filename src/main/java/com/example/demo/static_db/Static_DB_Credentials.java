package com.example.demo.static_db;

import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Static_DB_Credentials implements CommandLineRunner {

    @Autowired
    private _UserRepository userRepository;


    private void newUsers() {

        _User _user = new _User();
        _user.setName("Trener");
        _user.setLastname("Kowalski");
        _user.setEmail("tk1@example.com");
        _user.setPassword("12341234");
        _user.setPhone("12341234");
        _user.setRole("Trener");
        _user.setYear(1994);
        userRepository.save(_user);

        _User _user1 = new _User();
        _user1.setName("Trener2");
        _user1.setLastname("Kowalski2");
        _user1.setEmail("tk2@example.com");
        _user1.setPassword("12341234");
        _user1.setPhone("12341234");
        _user1.setRole("Trener");
        _user1.setYear(1995);
        userRepository.save(_user1);

        _User _user3 = new _User();
        _user3.setName("Trener3");
        _user3.setLastname("Kowalski3");
        _user3.setEmail("tk3@example.com");
        _user3.setPassword("12341234");
        _user3.setPhone("12341234");
        _user3.setRole("Trener");
        _user3.setYear(1999);
        userRepository.save(_user3);

        _User _user4 = new _User();
        _user4.setName("Zawodnik");
        _user4.setLastname("Kowalski");
        _user4.setEmail("1aryan@wp.pl");
        _user4.setPassword("12341234");
        _user4.setPhone("12341234");
        _user4.setRole("Zawodnik");
        _user4.setYear(1995);
        userRepository.save(_user4);
    }

  @Override
    public void run(String... args) throws Exception  {


        newUsers();
    }
}
