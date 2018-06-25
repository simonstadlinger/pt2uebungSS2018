package de.uni_potsdam.hpi;
import static org.junit.Assert.*;
import org.junit.*;

public abstract class DequeTest {
	Deque emptyDeque;
	Deque sevenDeque;
	Deque fullDeque;
	@Before
	public abstract void setUp() throws Exception;
		//leere Deque
		//Deque mit 7 Elementen
		//volle Deque
	
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
        for(int i = 0; i<6;i++)
        {
           assertEquals("object not null", null, emptyDeque.removeFirst());
        }

        assertEquals("objects not o", o, emptyDeque.removeFirst());
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
            emptyDeque.removeLast();
            fail("removeFirst() did not throw exception");
        }catch (DequeEmpty e){}
	}

	@Test
	public void TestG() throws Exception {
		//addFirst liefert für eine volle Deque eine Ausnahme.
        try{
            fullDeque.addFirst(new Object());
            fail("addFirst() did not throw exception");
        }catch (DequeFull e){}
	}
    //Tests, die nach der Code-Coverage Prüfung eingefügt wurden.
    @Test 
    public void TestH(){
        //capacity of emptyDeque liefert 10;
        assertEquals("capacity of konstructor and capacity() not equal", (long) 10, emptyDeque.capacity());
    }        
    @Test
    public void TestI() throws Exception{
        //addLast liefert für eine volle Deque eine Ausnahme.
        try{
            fullDeque.addLast(new Object());
            fail("addLast() did not throw exception");
        }catch (DequeFull e){}
    }
    @Test
	public void testJ() throws Exception{
		//removeFirst liefert für eine leere Deque eine Ausnahme.

        try{
            emptyDeque.removeFirst();
            fail("removeFirst() did not throw exception");
        }catch (DequeEmpty e){}
	}

    @Test 
    public void testK() throws Exception{
        Object e = new Object();
        emptyDeque.addFirst(e);
        assertEquals("emptyDeque after insert length is not 1", (long) 1, emptyDeque.size());
    }


	@After
	public void tearDown()throws Exception{
		//removes all objects
		emptyDeque = null;
		sevenDeque = null;
		fullDeque  = null;
	}
}

