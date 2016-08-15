import ua.rud.testingsystem.entities.CommonUtils;

public class Runner {
    public static void main(String[] args) {
        for (int j = 1; j <= 10; j++) {
            System.out.println(CommonUtils.getRandomString(32));
        }

    }
}

