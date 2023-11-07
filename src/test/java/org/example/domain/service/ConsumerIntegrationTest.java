package org.example.domain.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.di.MainModule;
import org.example.di.TestModule;
import org.example.domain.util.AddCommand;
import org.example.domain.util.Buffer;
import org.example.domain.util.DeleteCommand;
import org.example.domain.util.PrintCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsumerIntegrationTest {

    private Injector injector;
    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new TestModule(), new MainModule());
    }

    @Test
    public void testCommandProcessing() throws InterruptedException {
        Buffer buffer = injector.getInstance(Buffer.class);

        // redirect system out to capture it in stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        buffer.offer(new AddCommand(1L, "a1", "Robert"));
        buffer.offer(new AddCommand(2L, "a2", "Martin"));
        buffer.offer(new PrintCommand());
        buffer.offer(new DeleteCommand());
        buffer.offer(new PrintCommand());

        assertEquals("id: 1, group id: a1, name: Robert\n" +
                "id: 2, group id: a2, name: Martin\n", outputStream.toString());
    }
}
