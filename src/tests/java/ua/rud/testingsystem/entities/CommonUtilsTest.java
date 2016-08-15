package ua.rud.testingsystem.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(Theories.class)
public class CommonUtilsTest {

    /*stringArrayToIntList*/
    @Test
    public void stringArrayToIntList_onEmptyArray_returnEmptyList() {
        String[] stringArray = new String[0];
        List<Integer> expected = new ArrayList<>();

        List<Integer> actual = CommonUtils.stringArrayToIntList(stringArray);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void stringArrayToIntList_onFilledArray_returnFilledList() {
        String[] stringArray = new String[]{"0", "1", "2"};
        List<Integer> expected = new ArrayList<Integer>() {{
            add(0);
            add(1);
            add(2);
        }};

        List<Integer> actual = CommonUtils.stringArrayToIntList(stringArray);

        Assert.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    @Test(expected = NumberFormatException.class)
    public void stringArrayToIntList_onArrayContainsNotANumber_exceptionThrown() {
        String[] stringArray = new String[]{"0", "1", "XYZ"};
        CommonUtils.stringArrayToIntList(stringArray);
    }

    /*allEmpty*/
    @Test
    public void allEmpty_onEmptyArray_returnTrue() {
        String[] strings = new String[]{};
        boolean actual = CommonUtils.allEmpty(strings);
        Assert.assertTrue(actual);
    }

    @Test
    public void allEmpty_onNullArray_returnTrue() {
        boolean actual = CommonUtils.allEmpty(null);
        Assert.assertTrue(actual);
    }


    @Test
    public void allEmpty_onAllStringsEmpty_returnTrue() {
        String[] strings = new String[]{"", "", ""};
        boolean actual = CommonUtils.allEmpty(strings);
        Assert.assertTrue(actual);
    }

    @Test
    public void allEmpty_onNotAllStringsEmpty_returnFalse() {
        String[] strings = new String[]{"ABC", "XYZ", ""};
        boolean actual = CommonUtils.allEmpty(strings);
        Assert.assertFalse(actual);
    }

    @Test
    public void allEmpty_onStringsContainNull_returnFalse() {
        String[] strings = new String[]{"ABC", "XYZ", null};
        boolean actual = CommonUtils.allEmpty(strings);
        Assert.assertFalse(actual);
    }

    /*isFilled*/
    @Test
    public void isFilled_onEmptyArray_returnFalse() {
        String[] strings = new String[]{};
        boolean actual = CommonUtils.isFilled(strings);
        Assert.assertFalse(actual);
    }

    @Test
    public void isFilled_onNullArray_returnFalse() {
        boolean actual = CommonUtils.isFilled(null);
        Assert.assertFalse(actual);
    }

    @Test
    public void isFilled_onAllFilledStrings_returnTrue() {
        String[] strings = new String[]{"ABC", "XYZ", "123"};
        boolean actual = CommonUtils.isFilled(strings);
        Assert.assertTrue(actual);
    }

    @Test
    public void isFilled_onNotAllFilledStrings_returnFalse() {
        String[] strings = new String[]{"ABC", "XYZ", ""};
        boolean actual = CommonUtils.isFilled(strings);
        Assert.assertFalse(actual);
    }

    @Test
    public void isFilled_onStringsContainNull_returnFalse() {
        String[] strings = new String[]{"ABC", "XYZ", null};
        boolean actual = CommonUtils.isFilled(strings);
        Assert.assertFalse(actual);
    }

    @DataPoints
    public static int[][] data = {{-10, 0}, {0, 0}, {10, 10}};

    @Theory
    public void getRandomStringTest(int data[]) {
        Assert.assertTrue(CommonUtils.getRandomString(data[0]).length() == data[1]);
    }
    
}


