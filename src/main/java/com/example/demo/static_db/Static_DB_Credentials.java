package com.example.demo.static_db;

import com.example.demo.model.hall.HallDTO;
import com.example.demo.model.hall.HallRepository;

import com.example.demo.model.team.TeamDTO;
import com.example.demo.model.team.TeamRepository;
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
    @Autowired
    private TeamRepository teamRepository;

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


        _User _user4 = new _User();
        _user4.setName("Admin");
        _user4.setLastname("Admin");
        _user4.setEmail("1aryan@wp.pl");
        _user4.setPassword("12341234");
        _user4.setPhone("12341234");
        _user4.setRole("Admin");
        _user4.setCode("asdgsdgs");
        _user4.setYear(1995);
        _user4.setActivated(true);
        userRepository.save(_user4);

        _User _user8 = new _User();
        _user8.setName("Zawodnik");
        _user8.setLastname("BezDruzyny");
        _user8.setEmail("1a412421n@wp.pl");
        _user8.setPassword("12341234");
        _user8.setPhone("12341234");
        _user8.setRole("Zawodnik");
        _user8.setActivated(true);
        _user8.setYear(1995);
        userRepository.save(_user8);

        _User _user5 = new _User();
        _user5.setName("Andrzej");
        _user5.setLastname("Sedziowski");
        _user5.setEmail("sedzia@wp.pl");
        _user5.setPassword("12341234");
        _user5.setPhone("12341234");
        _user5.setRole("Sędzia");
        _user5.setYear(1985);
        _user5.setTeamDTO(teamRepository.findById(0));
        _user5.setActivated(true);
        userRepository.save(_user5);



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

        TeamDTO team1 = new TeamDTO();
        team1.setCity("Warszawa");
        team1.setDescription("Pzykladowa drużyna");
        team1.setName("COCO JAMBO WARSZAWA");
        teamRepository.save(team1);
    }
  @Override
    public void run(String... args) throws Exception  {
        newTeams();
        newHalls();
        newUsers();
            System.out.println(teamRepository.findAll());
    }
}
