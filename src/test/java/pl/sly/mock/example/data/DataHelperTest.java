package pl.sly.mock.example.data;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Example of mock static final DataManager used in DataHelper
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@PrepareForTest(DataManager.class)
public class DataHelperTest {
    @Autowired
    DataHelper dataHelper;

    @Test
    public void testResultOk() {
        //given
        PowerMockito.mockStatic(DataManager.class);
        Mockito.when(DataManager.getDataUrl()).thenReturn("connection");

        //when
        String result = dataHelper.invokeQuery();

        //then
        Assert.assertNotNull(result);
    }

    @Test
    public void testResultNotOk() {
        //given
        PowerMockito.mockStatic(DataManager.class);
        Mockito.when(DataManager.getDataUrl()).thenReturn(null);

        //when
        String result = dataHelper.invokeQuery();

        //then
        Assert.assertNull(result);
    }

    @Test
    public void testStaticReload() {
        //given
        PowerMockito.mockStatic(DataManager.class);

        //when
        dataHelper.invokeReload();

        //then
        PowerMockito.verifyStatic();
        DataManager.reload();
    }
}
