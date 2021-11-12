package Users;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import onetoone.Users.User;
import onetoone.Users.UserController;
import onetoone.Users.UserRepository;

public class UserTest
{
    @InjectMocks
    UserController userController;

    @Mock
    UserRepository userRepository;


    @SuppressWarnings("deprecation") //not needed
    @Before
    public void init()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserTest()
    {
        assertNull(userRepository.getUserById(Long.valueOf(1)));
        when(userRepository.getUserById(Long.valueOf(1))).thenReturn(new User("Cheehau", "cheehau@cheehau.com", "passwword", "Admin"));

        User user = userController.getPersonById(Long.valueOf(1));


        assertEquals("Cheehau", user.getName());
        assertEquals("cheehau@cheehau.com", user.getEmail());
        assertEquals("passwword", user.getPassword());
        assertEquals("Admin", user.getRoleType());


        verify(userRepository, times(2)).getUserById(Long.valueOf(1));
        verify(userRepository, times(2)).getUserById(anyLong());
    }

    @Test
    public void deleteUserTest()
    {
        verify(userRepository, never()).deleteUserById(anyLong());

        when(userRepository.getUserById(Long.valueOf(1))).thenReturn(new User("Cheehau", "cheehau@cheehau.com", "passwword", "Admin"));


        doNothing().when(userRepository).deleteUserById(Long.valueOf(1));
        String uCon = userController.deletePersonById(Long.valueOf(1));


        assertEquals("User deleted", uCon);



        verify(userRepository, atMost(1)).getUserById(anyLong()); 
        verify(userRepository, atMost(1)).deleteUserById(anyLong());

    }

    @Test
    public void getAllUserTest()
    {
        List<User> list = new ArrayList<User>();

        User user1 = new User("Cheehau", "Cheehau@cheehau.com", "password1", "Admin");
        User user2 = new User("Jayson", "Jayson@jayson.com", "password1", "User" );
        User user3 = new User("Vincent", "Vincent@voncent.com", "password2", "Support");

        list.add(user1);
        list.add(user2);
        list.add(user3);

        when(userController.getAllPeople()).thenReturn(list);

        List<User> userList = userController.getAllPeople();

        assertEquals(3, userList.size());

        verify(userRepository, never()).getUserById(anyLong());
    }
    @Test
    public void addUserTest()
    {
        User user = null;
        String UserCon = userController.addPerson(user);
        assertEquals("Failure", UserCon);

        user = new User("Cheehau","cheehau@cheehau.com","password","Admin");
        UserCon = userController.addPerson(user);
        assertEquals("User saved", UserCon);
    }
}
