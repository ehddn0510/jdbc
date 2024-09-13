import edu.sm.dto.Cust;

public class Test {
    public static void main(String[] args) {
        Cust c = Cust.builder()
                .id("id01")
                .pwd("pwd01")
                .build();

        System.out.println(c);
    }
}
