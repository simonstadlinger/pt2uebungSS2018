package de.uni_potsdam.hpi;
import static org.junit.Assert.*;
import org.junit.*;

public class ArrayDequeTest {
    ArrayDeque emptyDeque;
    ArrayDeque sevenDeque;
    ArrayDeque fullDeque;
	@Before
	public void setUp() throws Exception {
		//leere Deque
        emptyDeque = new ArrayDeque(10);
		//Deque mit 7 Elementen
        sevenDeque = new ArrayDeque(10);
        for(int i = 0; i<7;i++)
        {
            sevenDeque.addLast(new Object());
        }
		//volle Deque
        fullDeque = new ArrayDeque(2);
        for(int i = 0; i<2;i++)
        {
            fullDeque.addLast(new Object());
        }
	}

	@Test
	public void TestA() throws Exception{
        Object o = new Object();
        emptyDeque.addLast(o);
        assertEquals("inserted and removed Objects are not the same - empty Deque", emptyDeque.removeFirst(), o);
	}

	@Test
	public void TestB() throws Exception{
		//Fügt man in eine Deque mit 7 Elementen eines mit addLast ein, erhält man es mit removeLast sofort zurück.
        Object o = new Object();
        sevenDeque.addLast(o);
        assertEquals("inserted last and removed Objects last are not the same - seven element Deque", sevenDeque.removeLast(), o);
	}
	@Test
	public void TestC() throws Exception{
		//Fügt man in eine Deque mit 7 Elementen eines mit addFirst ein, erhält man es mit removeFirst sofort zurück.
        Object o = new Object();
        sevenDeque.addFirst(o);
        assertEquals("inserted first and removed first Objects are not the same - seven element Deque", sevenDeque.removeFirst(), o);
    }


	@Test
	public void TestD() throws Exception{
		//Fügt man in eine Deque 6 mal den Wert null ein und danach ein von null verschiedenes Objekt o , und führt danach wiederholt removeFirst aus, so erhält man 6 mal den Wert null , und danach o .
        for(int i = 0; i<6;i++)
        {
            emptyDeque.addLast(null);
        }
        Object o = new Object();
        emptyDeque.addLast(o);
        int null_count=0;
        int Object_count=0;
        for(int i = 0; i<6;i++)
        {
           assertEquals("object not null", emptyDeque.removeFirst(), o); 
        }
        assertEquals("objects not o", emptyDeque.removeFirst(), o);
	}


	@Test
	public void testE() throws Exception{
		//Nach Aufruf von clear liefert size den Wert 0.
        fullDeque.clear();
        assertEquals("Deque not empty after clear()", (long) 0, (long) fullDeque.size(), (long) 0);
	}

	@Test
	public void testF() throws Exception{
		//removeLast liefert für eine leere Deque eine Ausnahme.

        try{
            emptyDeque.removeFirst();
            fail("removeFirst() did not trow exception");
        }catch (DequeEmpty e){}
	}

	@Test
	public void TestG() {
		//addFirst liefert für eine volle Deque eine Ausnahme.
        try{
            fullDeque.addFirst(new Object());
            fail("addFirst() did not trow exception");
        }catch (DequeFull e){}
	}

	@After
	public void tearDown() {
		//removes all objects
	}
}
