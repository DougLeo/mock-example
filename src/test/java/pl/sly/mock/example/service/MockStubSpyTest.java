package pl.sly.mock.example.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class MockStubSpyTest {

    @Test(expected=IOException.class)
    public void testDoThrow() throws IOException {
        //given
        OutputStream streamMock = Mockito.mock(OutputStream.class);
        Mockito.doThrow(new IOException()).when(streamMock).close();
        OutputStreamWriter streamWriter = new OutputStreamWriter(streamMock);

        //when
        streamWriter.close();

        //then
        //exception expected
    }

    @Test
    public void testDoReturn() {
        //given
        DummyClass dummyClassMock = Mockito.mock(DummyClass.class);
        List<Integer> items = new ArrayList<>();
        Mockito.doReturn(items).when(dummyClassMock).dummyFoo();

        //when
        List<? extends Number> result = dummyClassMock.dummyFoo();

        //then
        Assert.assertEquals(items, result);
    }

    @Test
    public void testDoNothing() {
        //given
        DummyClass dummyClassSpy = Mockito.spy(new DummyClass());
        Mockito.doNothing().when(dummyClassSpy).doFoo();

        //when
        dummyClassSpy.doFoo();  //call mocked method

        //then
        Assert.assertNull(dummyClassSpy.getValue());    //null value expected
    }

    @Test
    public void testSpy() {
        //given
        DummyClass dummyClassSpy = Mockito.spy(new DummyClass());

        //when
        dummyClassSpy.doFoo();  //call real method

        //then
        Assert.assertEquals(dummyClassSpy.getValue(), Integer.valueOf(123));
    }

    private static class DummyClass {
        private Integer value;

        public List<? extends Number> dummyFoo() {
            return new ArrayList<>();
        }

        public void doFoo() {
            value = Integer.valueOf(123);
        }

        public Integer getValue() {
            return value;
        }
    }
}