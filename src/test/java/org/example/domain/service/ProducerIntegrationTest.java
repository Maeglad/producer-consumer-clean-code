package org.example.domain.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.di.MainModule;
import org.example.di.TestModule;
import org.example.domain.util.Buffer;
import org.example.domain.util.DeleteCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProducerIntegrationTest {

    private Injector injector;

    @BeforeEach
    public void setUp() {
        injector = Guice.createInjector(new TestModule(), new MainModule());
    }

    @Test
    public void testProducerDeleteCommandProduction() throws InterruptedException {
        Buffer buffer = injector.getInstance(Buffer.class);

        new ProducerService(buffer, 2).start();

        int deleteCount = 0;
        for (int i = 0; i < 10; ++i) {
            if (buffer.poll() instanceof DeleteCommand)
                ++deleteCount;
        }

        assertEquals(deleteCount, 5);
    }
}
