package ua.rud.testingsystem.entities;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(Theories.class)
public class CommonUtilsTest {

    @DataPoints("stringArrays")
    public static String[][] stringArray = {
            {"1", "2", "3"},
            {"1"}
    };

    @DataPoints("intLists")
    public static List[] intList = {
            new ArrayList<Integer>() {{
                add(1);
                add(2);
                add(3);
            }},
            new ArrayList<Integer>() {{
                add(1);
            }}
    };


    @Theory
    public void stringArrayToIntListTest(@FromDataPoints("stringArrays") String[] stringArray,
                                         @FromDataPoints("intLists") List<Integer> listInt) {
        List<Integer> actual = CommonUtils.stringArrayToIntList(stringArray);
//        System.out.println(actual);
//        System.out.println(listInt);
        System.out.println(actual);
//        Assert.assertTrue(listInt.containsAll(actual));
    }

    @Test
    public void allEmpty() throws Exception {

    }

    @Test
    public void isFilled() throws Exception {

    }

}