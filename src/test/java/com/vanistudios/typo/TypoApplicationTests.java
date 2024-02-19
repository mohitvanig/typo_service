package com.vanistudios.typo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vanistudios.typo.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vanistudios.typo.entity.User;
import com.vanistudios.typo.repository.UserRepository;
import com.vanistudios.typo.service.UserService;
import com.vanistudios.typo.serviceImpl.UserServiceImpl;

@SpringBootTest
class TypoApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	private UserService userService;  
	
	
	@Test
	public void test() {
		
		userService = new UserServiceImpl();
		User user = new User();
		User newUser = userService.createUser(user);
		assertEquals(user, newUser);
		
	}
	
	@MockBean // Mocking the UserRepository bean
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        // Mock behavior for userRepository.save()
        User user = new User(); // Create a user object or use a builder pattern
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User newUser = userService.createUser(user);

        assertEquals(user, newUser);
    }
}
