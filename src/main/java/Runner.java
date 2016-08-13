import ua.rud.testingsystem.entities.user.UserUtils;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(new A());
//        thread.start();
//        Thread.sleep(1000);
//        Thread thread1 = new Thread(new B());
//        thread1.start();

//        System.out.println(new SecureRandom().nextLong());

        Scanner scanner = new Scanner(System.in);
        String line;
        do {
            line = scanner.nextLine();
            System.out.println(UserUtils.isEmailValid(line));

        } while (!line.equals(""));
    }

}

class Test {


    static synchronized void y() {
        System.out.println("y");
        while (true) {
            Thread.yield();
        }
    }

    static synchronized void p() {
        System.out.println(Thread.currentThread().getName());

    }
}

class A implements Runnable {
    @Override
    public void run() {
        Test.y();
    }
}

class B implements Runnable {
    @Override
    public void run() {
        Test.p();
    }
}
