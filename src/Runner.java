import ua.rud.testingsystem.resource.MessageManager;

import java.sql.SQLException;
import java.util.Locale;

public class Runner {
    public static void main(String[] args) throws SQLException {
        Locale locale = new Locale("en");

        String property = MessageManager.getProperty("index.caption", locale);
        System.out.println(property);


//        MenuJdbc dao = MenuJdbc.getInstance();
//        List<Subject> subjects = dao.getSubjects();
//        System.out.println(subjects);
//
//        Subject subject = new Subject();
//        Map<Integer, String> tests = subject.getTests();
//        for (Map.Entry<Integer, String> integerStringEntry : tests.entrySet()) {
//        }

//        AuthorizationJdbc dao = new AuthorizationJdbc();

//        User user = dao.getUser(login, pass);

//        if (user != null) {
//            System.out.println(user);
//        } else {
//            System.out.println("Invalid login or password");
//        }

//        Dao dao = new Dao();
//        List<Test> tests = dao.getTests(1);
//        for (Test test : tests) {
//            System.out.println(test.getId() + "\t" + test.getSubject() + "\t" + test.getCaption());
//        }

//        List<Answer> answers = dao.getAnswers(ConnectorMySQL.getConnection(), 1);
//        for (Answer answer : answers) {
//            System.out.println(answer.getId() + "\t" + answer.getText() + "\t" + answer.isCorrect());
//        }

//        List<Test> tests = dao.getTests(1);
//        for (Test test : tests) {

//            System.out.println(test);
//        }

//        Test test = tests.get(1);
//        System.out.println(test);

//        dao.addTest(2,test);

    }
}
