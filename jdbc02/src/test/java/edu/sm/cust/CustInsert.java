package edu.sm.cust;

import edu.sm.dto.Cust;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.service.CustService;

public class CustInsert {
    public static void main(String[] args) {
        CustService custService = new CustService();
        Cust cust = Cust.builder()
                .id("id11")
                .pwd("pwd11")
                .name("이민")
                .build();

        try {
            custService.add(cust);
        }catch(DuplicatedIdException e){
            System.out.println("ID가 중복 되어 입력이 안됩니다.");
        } catch (Exception e) {
            System.out.println("시스템 장애");
        }


    }
}
