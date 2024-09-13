package edu.sm.cust;

import edu.sm.dto.Cust;
import edu.sm.service.CustService;

public class CustUpdate {
    public static void main(String[] args) {
        CustService custService = new CustService();
        Cust cust = Cust.builder()
                .id("id88")
                .pwd("pwd888")
                .name("홍말자")
                .build();

        try {
            custService.modify(cust);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}