package springbook.learningtest.template;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalcSumTest {
    Caculator caculator;
    String numFilePath;

    @Before
    public void setUp() {
        this.caculator = new Caculator();
        this.numFilePath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(caculator.calcSum(this.numFilePath), is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(caculator.calMultiply(this.numFilePath), is(24));
    }

    @Test
    public void concatenateStrings() throws IOException {
        assertThat(caculator.concatenate(this.numFilePath), is("1234"));
    }
}
