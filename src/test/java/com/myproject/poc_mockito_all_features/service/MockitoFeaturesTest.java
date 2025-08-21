package com.myproject.poc_mockito_all_features.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Demonstrates various Mockito features in a single test class.
 * 
 * Features included:
 *  - Mock creation
 *  - Stubbing methods
 *  - Verifying interactions
 *  - Argument matchers
 *  - Exception stubbing
 *  - Spy (partial mocks)
 *  - Capturing arguments
 *  - Mocking static methods
 *  - Mocking final classes & methods
 *  - Annotations (@Mock, @Spy, @InjectMocks, @Captor)
 */
@ExtendWith(MockitoExtension.class)
class MockitoFeaturesTest {

    /** Example of a simple dependency */
    @Mock
    private List<String> mockedList;

    /** Example of spying on a real object (partial mocking) */
    @Spy
    private List<String> spiedList = new java.util.ArrayList<>();

    /** Example of capturing arguments passed into methods */
    @Captor
    private ArgumentCaptor<String> captor;

    /**
     * Demonstrates stubbing and verification with a simple mock.
     */
    @Test
    void shouldStubAndVerifyMethods() {
        // Stubbing: defining behavior
        when(mockedList.get(0)).thenReturn("Hello Mockito");

        // Call the stubbed method
        String result = mockedList.get(0);

        // Verify result
        assertEquals("Hello Mockito", result);

        // Verify interaction
        verify(mockedList).get(0);
    }


     /**
     * Basic mocking:
     * Create a mock object and define its behavior.
     */
    @Test
    void testBasicMocking() {
        List<String> mockedList = mock(List.class);
        when(mockedList.get(0)).thenReturn("Hello Mockito");

        assertEquals("Hello Mockito", mockedList.get(0));
        assertNull(mockedList.get(1)); // Not stubbed
    }

    
    /**
     * Demonstrates the use of argument matchers.
     */
    @Test
    void shouldUseArgumentMatchers() {
        when(mockedList.get(anyInt())).thenReturn("Matched!");

        assertEquals("Matched!", mockedList.get(99));

        // Verify with matcher
        verify(mockedList).get(anyInt());
    }

    /**
     * Demonstrates exception stubbing.
     */
    @Test
    void shouldStubExceptions() {
        when(mockedList.get(1)).thenThrow(new RuntimeException("Boom!"));

        assertThrows(RuntimeException.class, () -> mockedList.get(1));
    }

    /**
     * Demonstrates partial mocking with spies.
     */
    @Test
    void shouldSpyOnRealObjects() {
        spiedList.add("one");
        spiedList.add("two");

        // Stub specific method
        when(spiedList.size()).thenReturn(100);

        assertEquals(100, spiedList.size());
        assertEquals("one", spiedList.get(0));

        // Verify that methods were actually called
        verify(spiedList).add("one");
        verify(spiedList).add("two");
    }

    /**
     * Demonstrates capturing arguments with ArgumentCaptor.
     */
    @Test
    void shouldCaptureArguments() {
        mockedList.add("captured");

        verify(mockedList).add(captor.capture());

        assertEquals("captured", captor.getValue());
    }

    /**
     * Demonstrates mocking static methods.
     */
    @Test
    void shouldMockStaticMethods() {
        try (MockedStatic<Math> mockedStatic = mockStatic(Math.class)) {
            mockedStatic.when(() -> Math.abs(-10)).thenReturn(42);

            assertEquals(42, Math.abs(-10));

            mockedStatic.verify(() -> Math.abs(-10));
        }
    }

    /**
     * Spies:
     * Wrap a real object and allow selective stubbing/verification.
     */
    @Test
    void testSpies() {
        List<String> list = new java.util.ArrayList<>();
        List<String> spyList = spy(list);

        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertEquals(2, spyList.size());
    }

     /**
     * InOrder verification:
     * Verify methods are called in a specific order.
     */
    @Test
    void testInOrderVerification() {
        List<String> firstMock = mock(List.class);
        List<String> secondMock = mock(List.class);

        firstMock.add("one");
        secondMock.add("two");

        InOrder inOrder = inOrder(firstMock, secondMock);
        inOrder.verify(firstMock).add("one");
        inOrder.verify(secondMock).add("two");
    }


    /**
     * Demonstrates mocking final classes.
     */
    @Test
    void shouldMockFinalClasses() {
        FinalClass finalMock = mock(FinalClass.class);
        when(finalMock.greet()).thenReturn("Mocked Final!");

        assertEquals("Mocked Final!", finalMock.greet());
    }

    /**
     * Exceptions:
     * Stub methods to throw exceptions.
     */
    @Test
    void testExceptions() {
        List<String> mockedList = mock(List.class);
        when(mockedList.get(0)).thenThrow(new RuntimeException("Error"));

        assertThrows(RuntimeException.class, () -> mockedList.get(0));
    }

    /**
     * Deep stubs:
     * Useful when mocking chained calls (avoids null pointers).
     * Example: mock a map where get() returns another mock automatically.
     */
    @Test
    void testDeepStubs() {
        Map<String, List<String>> deepStub = mock(Map.class, RETURNS_DEEP_STUBS);

        when(deepStub.get("key").get(0)).thenReturn("deep-value");

        assertEquals("deep-value", deepStub.get("key").get(0));
    }
}

    /** Example final class for demonstration */
    static final class FinalClass {
        String greet() {
            return "Hello from Final!";
        }
    }
}

