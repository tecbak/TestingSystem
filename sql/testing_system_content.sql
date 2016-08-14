/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (86,47,'Да',0),(87,47,'Нет',1),(88,48,'InputStream input = new InputStream(file);',0),(89,48,'InputStreamReader input = new InputStreamReader(new InputStream(file));',0),(90,48,'Reader input = new Reader(file);',0),(91,48,'BufferedReader input = new BufferedReader(new FileReader(file));',1),(92,48,'FileInputStream input = new FileInputStream(file);',1),(93,49,'static метод не может быть вызван из нестатического метода',0),(94,49,'final-метод не может быть статическим',0),(95,49,'private-метод не может быть вызван из другого метода этого класса',0),(96,49,'non-static метод не может быть вызван из статического метода без создания экземпляра соответствующего класса',1),(97,49,'non-static метод не может быть вызван из другого нестатического метода без создания экземпляра этого класса',0),(98,50,'public static void main(String args[])\r\n{...}',1),(99,50,'public static void main(String[] args)\r\n{...}',1),(100,50,'public static void main(String ... args)\r\n{...}',1),(101,50,'public void main(String args[])\r\n{...}',0),(102,50,'public static int main(String args[])\r\n{...}',0),(103,50,'public static int main{String args[]}\r\n{...}',0),(104,51,'p3.1',0),(105,51,'p3.1 p3.2 p2 p1',0),(106,51,'p3.1 p2',0),(107,51,'p3.1 p1',1),(108,51,'Ошибка компиляции',0),(109,51,'Ошибка времени выполнения',0),(114,54,'true',1),(115,54,'false',0),(116,55,'Да',0),(117,55,'Нет',1),(118,56,'Код не скомпилируется',0),(119,56,'Код скомпилируется, но во время выполнения будет выброшено исключение NullPointerException',0),(120,56,'Код скомпилируется, а на консоль будет выведено число \'0\'',1),(121,56,'Код скомпилируется, но ничего не выведет на консоль',0),(122,57,'Begin!',0),(123,57,'Execute!',0),(124,57,'Run!',1),(125,57,'Go!',0),(126,57,'Ничего напечатано не будет',0),(127,57,'Возникнет ошибка компиляции',0),(128,58,'false',0),(129,58,'true',1),(130,59,'<text>',0),(131,59,'<textfield>',0),(132,59,'<select>',0),(133,59,'<input>',1),(134,59,'<textbox>',0),(135,60,'Будет создан неупорядоченный список',1),(136,60,'Будет создан упорядоченный список',0),(137,60,'Тип созданного списка зависит от браузера',0),(138,60,'Каждый элемент списка будет начинаться с номера по порядку',0),(139,60,'Каждый элемент списка будет начинаться с маркера',1),(140,61,'Да',0),(141,61,'Нет',1);
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (47,58,'Можно ли при объявлении класса использовать модификаторы abstract и final одновременно?'),(48,58,'Что нужно вставить вместо ... чтобы прочитать строку \"Reading successful.\" из файла C:\\file.txt и вывести её на экран (укажите все подходящие варианты)?\r\n\r\npublic class Main { \r\n    public static void main(String[] args) { \r\n        File file = new File(\"C:\\\\file.txt\"); \r\n        try { \r\n            ... \r\n            int i; \r\n            while((i = input.read()) != -1){ \r\n                System.out.print((char)i); \r\n            } \r\n        } \r\n        catch (Exception ex) { \r\n            System.out.println(\"Exception\"); \r\n        } \r\n    } \r\n} '),(49,58,'Какие из следующих утверждений верны?'),(50,58,'Выберите все верные варианты объявления метода main (java 5 и выше).'),(51,58,'Какой результат выполнения программы:\r\n\r\npublic class Abc { \r\n    public static void main(String[] args) { \r\n    p1: \r\n        { \r\n        p2:    \r\n            { \r\n            p3: \r\n                { \r\n                    System.out.print(\"p3.1 \"); \r\n                    if (true) break p2; \r\n                    System.out.print(\"p3.2 \"); \r\n                } \r\n                System.out.print(\"p2 \"); \r\n            } \r\n            System.out.print(\"p1 \"); \r\n        } \r\n    } \r\n}'),(54,61,'Что напечатает следующий код:\r\n\r\npublic static void main(String[] args) { \r\n    Integer i = 10; \r\n    Integer x = 10; \r\n    if (x == i) { \r\n        System.out.print(\"true\"); \r\n    } else { \r\n        System.out.print(\"false\"); \r\n    } \r\n} '),(55,61,'Возможна ли перегрузка операторов в Java?'),(56,61,'public class Main { \r\n    public static void main(String[] args) { \r\n        System.out.println(args.length); \r\n    } \r\n}  '),(57,61,'Каким будет результат выполнения кода?\r\n\r\npublic class TestThread extends Thread {  \r\n    public void go() { System.out.println( \"Go!\" ); }  \r\n    public void run() { System.out.println( \"Run!\" ); }  \r\n    public void begin() { System.out.println( \"Begin!\" ); }  \r\n    public void execute() { System.out.println( \"Execute!\" ); }  \r\n \r\n    public static void main( String[] args ) {  \r\n        TestThread myTest = new TestThread();  \r\n        myTest.start();  \r\n    }  \r\n} '),(58,61,'Что будет выведено на консоль при вычислении следующего выражения?\r\n\r\nint x = 0; \r\nSystem.out.print(++x==x++); '),(59,62,'Какой html-тег создает поле ввода?'),(60,62,'Следующий фрагмент кода создает список:\r\n<ul>  \r\n<li>элемент 1</li>  \r\n<li>элемент 2</li> \r\n<li>элемент 3</li> \r\n</ul> '),(61,62,'Содержимое одного и того же HTML-документа отображается одинаково во всех браузерах.');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'Java'),(13,'HTML');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tests`
--

LOCK TABLES `tests` WRITE;
/*!40000 ALTER TABLE `tests` DISABLE KEYS */;
INSERT INTO `tests` VALUES (58,1,'Тест №1'),(61,1,'Тест №2'),(62,13,'Тест №1');
/*!40000 ALTER TABLE `tests` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
