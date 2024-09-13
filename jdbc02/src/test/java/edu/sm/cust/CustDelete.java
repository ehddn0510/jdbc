package edu.sm.cust;

import edu.sm.dto.Cust;
import edu.sm.service.CustService;

public class CustDelete {
    public static void main(String[] args) {
        CustService custService = new CustService();
        String id = "id88";

        try {
            custService.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}