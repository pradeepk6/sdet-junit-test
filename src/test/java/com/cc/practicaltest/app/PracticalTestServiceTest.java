package com.cc.practicaltest.app;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PracticalTestServiceTest {

    @Mock
    private PracticalTestRepository practicalTestRepository;
    @Mock
    private Clock clock;
    private Clock injectedClock;

    @InjectMocks
    private PracticalTestService practicalTestService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        injectedClock = Clock.systemDefaultZone();
        when(clock.instant()).thenReturn(injectedClock.instant());
        when(clock.getZone()).thenReturn(injectedClock.getZone());
    }

    @Test
    public void findOrdersSinceIfDateIsInThePast() {
        Order order1 = new Order(1, "fn1", "sn1");
        Order order2 = new Order(2, "fn2", "sn2");
        LocalDate date = LocalDate.of(2019, 1, 1);
        when(practicalTestRepository.getOrdersOnOrAfter(date)).thenReturn(Arrays.asList(order1, order2));
        assertTrue(practicalTestService.findOrdersSince(date).size() == 2);
    }

    @Test(expected = InvalidDateException.class)
    public void findOrdersSinceIfDateIsInTheFuture() {
        LocalDate date = LocalDate.of(2040, 1, 1);
        practicalTestService.findOrdersSince(date);
    }
}
