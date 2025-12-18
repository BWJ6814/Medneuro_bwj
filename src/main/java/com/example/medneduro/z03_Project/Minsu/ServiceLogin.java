package com.example.medneduro.z03_Project.Minsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Transactional
@Service
public class ServiceLogin {

    @Autowired(required = false)
    private DAOLogin dao;

    // 로그인
    public boolean logincheck(String userType, String id, String pwd) {
        String dbType = "";
        if("general".equals(userType)) dbType = "G";
        if("doctor".equals(userType)) dbType = "D";

        Map<String, String> map1 = new HashMap<>();
        map1.put("ID", id);
        map1.put("PWD", pwd);
        map1.put("USER_TYPE", dbType);
        return dao.logincheck(map1) > 0;
    }

    public int checkId(String id){
        return dao.checkId(id);
    }

    // ★ 회원가입 프로세스
    public String registerProc(Register reg) {

        // 1. 중복 체크
        if(dao.checkId(reg.getId()) > 0) return "DUPLICATE_ID";

        // 2. 성별 변환 (환자용)
        String rawGender = reg.getGender();
        if("1".equals(rawGender) || "3".equals(rawGender)) reg.setGender("M");
        else if("2".equals(rawGender) || "4".equals(rawGender)) reg.setGender("F");

        // 3. 타입 변환
        String rawType = reg.getUserType();
        if("general".equals(rawType)) reg.setUserType("G");
        if("doctor".equals(rawType)) reg.setUserType("D");

        // 4. 분기 처리
        if("G".equals(reg.getUserType())) {
            // === [환자] 이름+폰+생일+성별 ===
            Integer existingAccountId = dao.findPatientAccountId(reg);

            if(existingAccountId == null) return "PATIENT_NOT_FOUND";
            if(existingAccountId != 0) return "ALREADY_REGISTERED";

            dao.insertAccount(reg);
            if(dao.linkPatientAccount(reg) == 0) throw new RuntimeException("환자 연결 실패");

        } else if("D".equals(reg.getUserType())) {
            // === [의료진] 이름+면허번호+부서코드 ===

            // 1) 매칭 확인 (이름, 면허번호, 부서코드)
            Integer existingAccountId = dao.findMedicalStaffAccountId(reg);

            // 2) 정보 없음 (입력한 면허번호/부서가 틀렸거나 등록된 의사가 아님)
            if(existingAccountId == null) return "STAFF_NOT_FOUND";

            // 3) 이미 가입됨
            if(existingAccountId != 0) return "ALREADY_REGISTERED";

            // 4) 가입 진행
            dao.insertAccount(reg);

            // 5) 연결
            if(dao.linkMedicalStaffAccount(reg) == 0) throw new RuntimeException("의료진 연결 실패");
        }

        return "SUCCESS";
    }
}