package com.example.demo.static_db;

import com.example.demo.model.hall.HallDTO;
import com.example.demo.model.hall.HallRepository;

import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Static_DB_Credentials implements CommandLineRunner {

    @Autowired
    private _UserRepository userRepository;

    @Autowired
    private HallRepository hallRepository;


    private void newUsers() {

        _User _user = new _User();
        _user.setName("Trener");
        _user.setLastname("Kowalski");
        _user.setEmail("tk1@example.com");
        _user.setPassword("12341234");
        _user.setPhone("12341234");
        _user.setRole("Trener");
        _user.setActivated(true);

        _user.setYear(1994);
        userRepository.save(_user);

        _User _user1 = new _User();
        _user1.setName("Trener2");
        _user1.setLastname("Kowalski2");
        _user1.setEmail("tk2@example.com");
        _user1.setPassword("12341234");
        _user1.setPhone("12341234");
        _user1.setRole("Trener");
        _user1.setActivated(true);
        _user1.setYear(1995);
        userRepository.save(_user1);

        _User _user3 = new _User();
        _user3.setName("Trener3");
        _user3.setLastname("Kowalski3");
        _user3.setEmail("tk3@example.com");
        _user3.setPassword("12341234");
        _user3.setPhone("12341234");
        _user3.setRole("Trener");
        _user3.setActivated(true);
        _user3.setYear(1999);
        userRepository.save(_user3);

        _User _user4 = new _User();
        _user4.setName("Zawodnik");
        _user4.setLastname("Kowalski");
        _user4.setEmail("1aryan@wp.pl");
        _user4.setPassword("12341234");
        _user4.setPhone("12341234");
        _user4.setRole("Zawodnik");
        _user4.setCode("asdgsdgs");
        _user4.setYear(1995);
        _user4.setActivated(true);
        userRepository.save(_user4);

        _User _user5 = new _User();
        _user5.setName("Andrzej");
        _user5.setLastname("Sedziowski");
        _user5.setEmail("sedzia@wp.pl");
        _user5.setPassword("12341234");
        _user5.setPhone("12341234");
        _user5.setRole("Sędzia");
        _user5.setYear(1985);
        _user5.setActivated(true);
        userRepository.save(_user5);

        _User _user6 = new _User();
        _user6.setName("Patryk");
        _user6.setLastname("Sędziowski");
        _user6.setEmail("1ar5125an@wp.pl");
        _user6.setPassword("12341234");
        _user6.setPhone("12341234");
        _user6.setRole("Sędzia");
        _user6.setCode("asdgsdgs");
        _user6.setYear(1995);
        _user6.setActivated(true);
        userRepository.save(_user6);


    }
    private void newHalls(){


        HallDTO hall1 = new HallDTO();
        hall1.setAdress("Złota");
        hall1.setCity("Warszawa");
        hall1.setNumber("55");
        hall1.setPrice("200");
        hall1.setActivated(true);
        hallRepository.save(hall1);

        HallDTO hall2 = new HallDTO();
        hall2.setAdress("Agawy");
        hall2.setCity("Krakow");
        hall2.setNumber("13");
        hall2.setPrice("115");
        hall2.setActivated(true);
        hallRepository.save(hall2);

        HallDTO hall3 = new HallDTO();
        hall3.setAdress("Jakaśulica");
        hall3.setCity("Siedlce");
        hall3.setNumber("14b");
        hall3.setPrice("65");
        hall3.setActivated(true);
        hall3.setDescription("Duża hala z trybunami przy moście");
        hallRepository.save(hall3);

        HallDTO hall4 = new HallDTO();
        hall4.setAdress("Żytnia");
        hall4.setCity("Kielce");
        hall4.setNumber("15");
        hall4.setPrice("140");
        hall4.setActivated(true);
        hall4.setDescription("3000 miejsc na trybunach, scena, miękie deski, nowy parkiet, centralnie obok MC Donald's");
        hallRepository.save(hall4);
    }
    private void newTeams() {
//
//        TeamDTO team1 = new TeamDTO();
//        team1.setCity("Nicość");
//        team1.setDescription("NIC");
//        team1.setName("Brak");
//        teamRepository.save(team1);
    }
  @Override
    public void run(String... args) throws Exception  {
        newTeams();
        newHalls();
        newUsers();

    }
}
