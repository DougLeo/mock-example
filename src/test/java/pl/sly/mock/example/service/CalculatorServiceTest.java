package pl.sly.mock.example.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.sly.mock.example.model.dto.Calculation;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class CalculatorServiceTest {
    @Resource
    CalculatorService calculatorService;

    @Spy
    Calculation calculation;

    @Test
    public void testInvokeCalculation() {
        //given
        calculation = new Calculation();

        //when
        calculatorService.invokeCalculation(calculation);

        //then
        Assert.assertEquals(Long.valueOf(123), calculation.getResult());
    }
}
