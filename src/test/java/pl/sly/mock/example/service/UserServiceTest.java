package pl.sly.mock.example.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import pl.sly.mock.example.model.entity.UserEntity;
import pl.sly.mock.example.repository.UserRepository;
import pl.sly.mock.example.utils.FactoryTestUtils;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class UserServiceTest {
    @Resource
    UserService userService;

    @Mock
    UserRepository userRepositoryMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(userService, "userRepository", userRepositoryMock);
    }

    @Test
    public void shouldPassGetAllUsersTest() {
        //given
        List<UserEntity> userEntityList = FactoryTestUtils.createUserModelList(10);
        Mockito.when(userRepositoryMock.findAll()).thenReturn(userEntityList);

        //when
        List<UserEntity> result = userService.findAllUsers();

        //then
        Assert.assertNotNull(result);
        Assert.assertEquals(userEntityList.size(), result.size());
    }
}