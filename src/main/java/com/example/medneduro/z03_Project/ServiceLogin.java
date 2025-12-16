package com.example.medneduro.z03_Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceLogin {
    @Autowired(required = false)
    private DAOLogin dao;

    public boolean logincheck(String userType, String id, String pwd) {
        String dbType = "";
        if(userType.equals("general")){
            dbType = "G";
        }
        if(userType.equals("doctor")){
            dbType = "D";
        }
        Map<String, String> map1 = new HashMap<>();
        map1.put("ID",id);
        map1.put("PWD",pwd);
        map1.put("USER_TYPE",dbType);
        return dao.logincheck(map1) > 0;
    }

}
