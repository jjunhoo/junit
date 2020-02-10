package junit;

import junit.chapter2.*;
import org.junit.Test;

public class ProfileTest {
    // 단일 경로 커버
    @Test
    public void test() {
        // 1. 프로파일 생성
        Profile profile = new Profile("Bull Hockey, Inc.");
        // 2. 질문 생성 (보너스를 받았나요 ?)
        Question question = new BooleanQuestion(1, "Got bonuses?");
        // 3. Criteria 생성 및 가중치 저장
        Criteria criteria = new Criteria();
        // 4. 질문과 기대하는 정답 (Bool.TRUE) 포함
        Answer criteriaAnswer = new Answer(question, Bool.TRUE);
        // 5. criterion 객체 추가
        Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);

        criteria.add(criterion);
    }
}
