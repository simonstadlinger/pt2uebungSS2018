package de.uni_potsdam.hpi;

import static org.junit.Assert.*;
import org.junit.*;

public class ArrayDequeTest extends DequeTest{

    @Before
    public void setUp() throws Exception {
        //leere Deque
        emptyDeque = new ArrayDeque(10);
        //Deque mit 7 Elementen
        sevenDeque = new ArrayDeque(10);
        for(int i = 0; i < 7; i++)
        {
            sevenDeque.addLast(new Object());
        }
        //volle Deque
        fullDeque = new ArrayDeque(2);
        for(int i = 0; i < 2; i++)
        {
            fullDeque.addLast(new Object());
    	}
	}
}
