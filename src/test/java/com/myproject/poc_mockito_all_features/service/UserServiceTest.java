package com.myproject.poc_mockito_all_features.service;

import com.myproject.poc_mockito_all_features.model.User;
import com.myproject.poc_mockito_all_features.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserService service;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnUserNameWhenUserExists() {
        when(repository.findById("123")).thenReturn(Optional.of(new User("123", "Alice")));

        String result = service.getUserName("123");

        assertEquals("Alice", result);
    }

    @Test
    void shouldReturnUnknownWhenUserNotExists() {
        when(repository.findById("999")).thenReturn(Optional.empty());

        String result = service.getUserName("999");

        assertEquals("Unknown", result);
    }

    @Test
    void shouldSaveUserCorrectly() {
        service.registerUser("456", "Bob");

        verify(repository).save(userCaptor.capture());
        User captured = userCaptor.getValue();
        assertEquals("Bob", captured.getName());
        assertEquals("456", captured.getId());
    }

    @Test
    void shouldThrowExceptionWhenSaveFails() {
        doThrow(new RuntimeException("DB Error")).when(repository).save(any());

        assertThrows(RuntimeException.class, () -> {
            service.registerUser("789", "Eve");
        });
    }

    @Test
    void testSequentialStubbing() {
        when(repository.findById("1"))
                .thenReturn(Optional.empty())
                .thenReturn(Optional.of(new User("1", "RetryUser")));

        assertEquals("Unknown", service.getUserName("1"));
        assertEquals("RetryUser", service.getUserName("1"));
    }

    @Test
    void testSpyExample() {
        User user = new User("777", "Original");
        User spyUser = spy(user);

        when(spyUser.getName()).thenReturn("SpyName");

        assertEquals("SpyName", spyUser.getName());
        verify(spyUser).getName();
    }

    @Test
    void testStaticMocking() {
        try (MockedStatic<Math> mathMock = mockStatic(Math.class)) {
            mathMock.when(() -> Math.max(5, 10)).thenReturn(100);
            assertEquals(100, Math.max(5, 10));
        }
    }
}
