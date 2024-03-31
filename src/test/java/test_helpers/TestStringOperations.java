package test_helpers;

import helpers.StringOperations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestStringOperations {

    @Test
    public void testMergeThreeStrings() {
        String str1 = "This is a long string";
        String str2 = "Second string";
        String str3 = "Third string";
        String expected = "This is a long string   Second string            Third string";
        String result = StringOperations.mergeThreeStrings(str1, str2, str3);
        assertEquals(expected, result);
    }
    @Test
    public void testMergeThreeStrings2() {
        String str1 = "This is a long stringgggggggggggg";
        String str2 = "Second string is upper than 50 letter and we are testing now ,event planner team";
        String str3 = "Third string";
        String expected = "This is a long stringgggSecond string is upper thThird string";
        String result = StringOperations.mergeThreeStrings(str1, str2, str3);
        assertEquals(expected, result);
    }

}


