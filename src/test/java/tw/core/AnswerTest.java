package tw.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tw.core.exception.AnswerFormatIncorrectException;
import tw.core.model.Record;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by jxzhong on 2017/9/23.
 */
public class AnswerTest {
    private Answer actualAnswer;

    @BeforeEach
    public void setUp() {
        actualAnswer = Answer.createAnswer("1 2 3 4");
    }

    @Test
    public void should_get_right_answer_when_call_createAnswer_given_an_input_string() {
        String inputString = "1 2 3 4";
        Answer answer = Answer.createAnswer(inputString);

        assertThat(answer.toString(), is("1 2 3 4"));
    }

    @Test
    public void should_success_when_call_validate_given_correct_format_answer() {
        Answer answer = Answer.createAnswer("1 2 3 4");

        try {
            answer.validate();
        } catch (AnswerFormatIncorrectException e) {
            fail("should validate success");
        }
    }

    @Test
    public void should_unsuccess_when_call_validate_given_incorrect_format_answer() {
        Answer answer = Answer.createAnswer("1 1 1 2");

        try {
            answer.validate();
            fail("should validate unsuccess");
        } catch (AnswerFormatIncorrectException e) {

        }
    }

    @Test
    public void should_get_right_record_when_call_check_given_an_answer() {
        Answer answer = Answer.createAnswer("1 2 3 4");

        Record record = answer.check(Answer.createAnswer("1 4 3 9"));

        assertThat(record.getValue(), is("2A1B"));
    }
}