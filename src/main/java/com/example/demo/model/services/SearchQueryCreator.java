package com.example.demo.model.services;

import com.example.demo.model.hall.HallDTO;
import com.example.demo.model.search.SearchTeamRequest;
import com.example.demo.model.search.SearchUserRequest;
import com.example.demo.model.team.TeamDTO;
import com.example.demo.model.user._User;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.EntityManager;
@Service
public class SearchQueryCreator {

    @PersistenceContext
    private EntityManager entityManager;

    public SearchQueryCreator(){}

    public  String buildQuery(SearchUserRequest request){

    StringBuilder stringBuilder = new StringBuilder("SELECT _user.* from _user WHERE ");
    if(request.getEmail()!=null && !request.getEmail().equals("") ) stringBuilder.append("UPPER(_user.email) = UPPER('" + request.getEmail()+"') AND ");

    if(request.getName()!=null && !request.getName().equals("") )  stringBuilder.append("UPPER(_user.name) = UPPER('"+request.getName()+"') AND ");

    if(request.getLastname()!=null && !request.getLastname().equals("") ) stringBuilder.append("UPPER(_user.lastname) = UPPER('"+request.getLastname()+"') AND ");

    if(request.getRole()!=null && !request.getRole().equals("Wszystkie")) stringBuilder.append("_user.role = '"+request.getRole()+"' AND ");


    stringBuilder.append("(_user.is_activated = true)");
    return stringBuilder.toString();
    }

    public  String buildQuery(HallDTO request){

        StringBuilder stringBuilder = new StringBuilder("SELECT halldto.* from halldto WHERE ");
        if(request.getCity()!=null && !request.getCity().equals("") ) stringBuilder.append("UPPER(halldto.city) = UPPER('" + request.getCity()+"') AND ");

        if(request.getAdress()!=null && !request.getAdress().equals("") )  stringBuilder.append("UPPER(halldto.adress) = UPPER('"+request.getAdress()+"') AND ");

        if(request.getNumber()!=null && !request.getNumber().equals("") ) stringBuilder.append("UPPER(halldto.number) = UPPER('"+request.getNumber()+"') AND ");

        stringBuilder.append("(halldto.is_activated = true)");
        return stringBuilder.toString();
    }

    public  String buildQuery(SearchTeamRequest request){

        StringBuilder stringBuilder = new StringBuilder("SELECT teamdto.* from teamdto WHERE 1=1 ");
        if(request.getCity()!=null && !request.getCity().equals("") ) stringBuilder.append("AND UPPER(teamdto.city) = UPPER('" + request.getCity()+"')");

        if(request.getName()!=null && !request.getName().equals("") )  stringBuilder.append("AND UPPER(teamdto.name) = UPPER('"+request.getName()+"')");


        return stringBuilder.toString();
    }


    public  List<HallDTO> executeHallQuery(String sqlQuery){
        Query query = entityManager.createNativeQuery(sqlQuery, HallDTO.class);
        return query.getResultList();
    }

    public  List<HallDTO> buildAndExecuteHallQuery(HallDTO request){
        String sqlQuery = buildQuery(request);
        return executeHallQuery(sqlQuery);
    }

    public  List<TeamDTO> executeTeamQuery(String sqlQuery){
        Query query = entityManager.createNativeQuery(sqlQuery, TeamDTO.class);
        return query.getResultList();
    }

    public  List<TeamDTO> buildAndExecuteTeamQuery(SearchTeamRequest request){
        String sqlQuery = buildQuery(request);
        return executeTeamQuery(sqlQuery);
    }


    public  List<_User> executeUserQuery(String sqlQuery){
        Query query = entityManager.createNativeQuery(sqlQuery, _User.class);
        return query.getResultList();
    }
    public  List<_User> buildAndExecuteUserQuery(SearchUserRequest request){
        String sqlQuery = buildQuery(request);
        return executeUserQuery(sqlQuery);
    }
}

