public class Runner {
    public static void main(String[] args) throws InterruptedException {

        try {
            try {
                throw new IllegalArgumentException();
            } finally {

            }

        } catch (Exception e) {
            System.out.println("caught");
        }

        System.out.println("".startsWith("a"));
    }
}

