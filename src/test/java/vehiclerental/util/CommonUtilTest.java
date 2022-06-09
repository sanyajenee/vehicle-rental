package vehiclerental.util;

import com.vehiclerental.util.CommonUtils;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CommonUtilTest {
    @Test
    void testSplitIntoList() {
        List<String> actual = CommonUtils.splitIntoList("  IS THIS   THE REAL LIFE", " ");
        List<String> expected = List.of("IS", "THIS", "THE", "REAL", "LIFE");

        assertLinesMatch(expected, actual);
    }

    @Test
    void testIsInteger() {
        assertFalse(CommonUtils.isInteger("ABCDE"));
        assertTrue(CommonUtils.isInteger("69887"));
    }

    @Test
    void testIsValidTime() {
        assertFalse(CommonUtils.isValidTime(-2));
        assertFalse(CommonUtils.isValidTime(27));
        assertTrue(CommonUtils.isValidTime(24));
    }

    @Test
    void testAreSetsEqual() {
        Set<String> s1 = new HashSet<>(List.of("A", "B"));
        Set<String> s2 = new HashSet<>(List.of("A", "B", "C"));

        assertFalse(CommonUtils.areSetsEqual(s1, s2));

        s2.remove("C");
        assertTrue(CommonUtils.areSetsEqual(s1, s2));

        assertFalse(CommonUtils.areSetsEqual(null, s2));
    }
}
