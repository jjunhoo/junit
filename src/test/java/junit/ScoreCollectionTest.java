package junit;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*; // equalTo 메소드 사용을 위한 static import

import junit.chapter2.ScoreCollection;
import org.junit.*;

public class ScoreCollectionTest {
    @Test
    public void answersArithmeticMeanOfTwoNumbers() {
        // 준비 (given)
        ScoreCollection collection = new ScoreCollection();
        collection.add(() -> 5);
        collection.add(() -> 7);
        // 실행 (when)
        int actualResult = collection.arithmeticMean();
        // 단언 (then)
        //assertThat(actualResult, equalTo(6)); // 성공 케이스
        assertThat(actualResult, equalTo(3)); // 실패 케이스 (Expected : 3 / Actual : 6)
    }
    @Test
    public void test() {
        //Fail.fail("Not yet implemented");
    }
}