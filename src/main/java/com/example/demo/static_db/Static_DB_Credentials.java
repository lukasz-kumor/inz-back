package com.example.demo.static_db;

import com.example.demo.model.hall.HallDAO;
import com.example.demo.model.hall.HallRepository;

import com.example.demo.model.match.MatchDAO;
import com.example.demo.model.match.MatchRepository;
import com.example.demo.model.team.TeamDAO;
import com.example.demo.model.team.TeamRepository;
import com.example.demo.model.user._User;
import com.example.demo.model.user._UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class Static_DB_Credentials implements CommandLineRunner {

    @Autowired
    private _UserRepository userRepository;

    @Autowired
    private HallRepository hallRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MatchRepository matchRepository;

    private void newUsers() {
        ///druzyny
        TeamDAO team1 = new TeamDAO();
        team1.setCity("Warszawa");
        team1.setDescription("Drużyna nr 1");
        team1.setName("TK1 Team");
        teamRepository.save(team1);

        TeamDAO team2 = new TeamDAO();
        team2.setCity("Pcim Dolny");
        team2.setDescription("Drużyna nr 2");
        team2.setName("TK2 Team");
        teamRepository.save(team2);
        ///admin
        _User _user1 = new _User();
        _user1.setName("Łukasz");
        _user1.setLastname("Kumor");
        _user1.setEmail("1aryan@wp.pl");
        _user1.setPassword("1234");
        _user1.setPhone("668087723");
        _user1.setRole("Admin");
        _user1.setYear(1995);
        _user1.setActivated(true);
        userRepository.save(_user1);


        //trenerzy
        _User _user2 = new _User();
        _user2.setName("Trener");
        _user2.setLastname("Kowalski");
        _user2.setEmail("t1@wp.pl");
        _user2.setPassword("1234");
        _user2.setPhone("938473627");
        _user2.setTeamDAO(team1);
        _user2.setRole("Trener");
        _user2.setActivated(true);
        _user2.setYear(1980);
        userRepository.save(_user2);

        _User _user3 = new _User();
        _user3.setName("DrugiTrener");
        _user3.setLastname("Janowski");
        _user3.setEmail("t2@wp.pl");
        _user3.setPassword("1234");
        _user3.setPhone("605847362");
        _user3.setTeamDAO(team2);
        _user3.setRole("Trener");
        _user3.setActivated(true);
        _user3.setYear(1980);
        userRepository.save(_user3);

        ///playerzy
//

        for(int i=0;i<10;i++) {
            _User user = new _User();
           if(i%2==0) user.setTeamDAO(team1);
            user.setName("Zawodnik[" + i + "]");
            user.setActivated(true);
            user.setRole("Zawodnik");
            user.setPassword("1234");
            user.setPhone("607837493");
            user.setEmail("z" + i + "@wp.pl");
            user.setLastname("Kowalski");
            user.setYear(1990 + i);
            userRepository.save(user);
        }





        for(int i=0;i<7;i++){
            _User user = new _User();
            user.setTeamDAO(team2);
            user.setName("Player["+i+"]");
            user.setActivated(true);
            user.setRole("Zawodnik");
            user.setPassword("1234");
            user.setPhone("784384039");
            user.setEmail("p"+i+"@wp.pl");
            user.setLastname("Janowski");
            user.setYear(1993+i);
            userRepository.save(user);
        }
        //sedziowie
        for(int i=0;i<3;i++){
            _User user = new _User();

            user.setName("Sędzia["+i+"]");
            user.setActivated(true);
            user.setRole("Sędzia");
            user.setPhone("849384758");
            user.setPassword("1234");
            user.setEmail("s"+i+"@wp.pl");
            user.setLastname("Petkow");
            user.setYear(1980+i);
            userRepository.save(user);
        }

    }
    private void newHalls(){


        HallDAO hall1 = new HallDAO();
        hall1.setAdress("Złota");
        hall1.setCity("Warszawa");
        hall1.setNumber("55");
        hall1.setPrice("200");
        hall1.setActivated(true);
        hallRepository.save(hall1);

        HallDAO hall2 = new HallDAO();
        hall2.setAdress("Agawy");
        hall2.setCity("Krakow");
        hall2.setNumber("13");
        hall2.setPrice("115");
        hall2.setActivated(false);
        hallRepository.save(hall2);



        HallDAO hall4 = new HallDAO();
        hall4.setAdress("Żytnia");
        hall4.setCity("Kielce");
        hall4.setNumber("15");
        hall4.setPrice("140");
        hall4.setActivated(true);
        hall4.setDescription("3000 miejsc na trybunach, scena, miękie deski, nowy parkiet, centralnie obok MC Donald's");
        hallRepository.save(hall4);
    }
 private void newMatches(){

     HallDAO hall3 = new HallDAO();
     hall3.setAdress("Jakaśulica");
     hall3.setCity("Siedlce");
     hall3.setNumber("14b");
     hall3.setPrice("65");
     hall3.setActivated(true);
     hall3.setDescription("Duża hala z trybunami przy moście");
     hallRepository.save(hall3);

     MatchDAO match1 = new MatchDAO();
     match1.setActive(true);
     match1.setHall(hall3);
     match1.setTeamAid(30);
     match1.setTeamBid(31);
     match1.setBeginDate(new Date());
     match1.setRefId(60);

     match1.setTeamBaccepted(true);
     match1.setRefAccepted(true);
     match1.setSalary(200);
     matchRepository.save(match1);

 }
  @Override
    public void run(String... args) throws Exception  {
        newMatches();
        newHalls();
        newUsers();

    }
}
