package pl.sly.mock.example.helper;

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
 * Example of mock static final DatabaseManager used in QueryHelper
 */
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@PrepareForTest(DatabaseManager.class)
public class QueryHelperTest {
    @Autowired
    QueryHelper queryHelper;

    @Test
    public void testResultOk() {
        //given
        PowerMockito.mockStatic(DatabaseManager.class);
        Mockito.when(DatabaseManager.getConnectionUrl()).thenReturn("connection");

        //when
        String result = queryHelper.invokeQuery();

        //then
        Assert.assertNotNull(result);
    }

    @Test
    public void testResultNotOk() {
        //given
        PowerMockito.mockStatic(DatabaseManager.class);
        Mockito.when(DatabaseManager.getConnectionUrl()).thenReturn(null);

        //when
        String result = queryHelper.invokeQuery();

        //then
        Assert.assertNull(result);
    }

    @Test
    public void testStaticReload() {
        //given
        PowerMockito.mockStatic(DatabaseManager.class);

        //when
        queryHelper.callStaticReload();

        //then
        PowerMockito.verifyStatic();
        DatabaseManager.reload();
    }
}
