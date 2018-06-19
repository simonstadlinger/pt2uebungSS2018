import static org.junit.Assert.*;
import org.junit.*;

public class DequeTest {

	@Before
	public void setUp() {
		//leere Deque
        Deque q = new Deque();
		//Deque mit 7 Elementen
        
		//volle Deque
	}

	@Test
	public void TestA() {
        Object o = new Object();
        q.addLast(o);
        assertEquals("inserted and removed Objects are not the same - empty Deque", q.removeFirst(), o);
	}

	@Test
	public void TestB() {
		//Fügt man in eine Deque mit 7 Elementen eines mit addLast ein, erhält man es mit removeLast sofort zurück.
        Object o = new Object();
        q7.addLast(o);
        assertEquals("inserted last and removed Objects last are not the same - seven element Deque", q7.remoteLast(), o);
	}
	@Test
	public void TestC() {
		//Fügt man in eine Deque mit 7 Elementen eines mit addFirst ein, erhält man es mit removeFirst sofort zurück.
        Object o = new Object();
        q7.addFirst(o);
        assertEquals("inserted fist and removed first Objects are not the same - seven element Deque", q7.remoteFirst(), o);
    }


	@Test
	public void TestD() {
		//Fügt man in eine Deque 6 mal den Wert null ein und danach ein von null verschiedenes Objekt o , und führt danach wiederholt removeFirst aus, so erhält man 6 mal den Wert null , und danach o .
        for(int i = 0; i<6;i++)
        {
            q.addLast(null);
        }
        Object o = new Object();
        q.addLast(o);
        int null_count=0;
        int Object_count=0;
        for(int i = 0; i<6;i++)
        {
           assertEquals("object not null", q.removeFirst(), o); 
        }
        asserEquals("objects not o", q.removeFirst(), o);
	}


	@Test
	public void testE() {
		//Nach Aufruf von clear liefert size den Wert 0.
        q_full.clear();
        assertEquals("Deque not empty after clear()", (long) 0, (long) q_full.size(), (long) 0);
	}

	@Test
	public void testF() {
		//removeLast liefert für eine leere Deque eine Ausnahme.

        try{
            q.removeFist();
            fail("removeFirst() did not trow exception");
        }catch (DequeEmpty e){}
	}

	@Test
	public void TestG() {
		//addFirst liefert für eine volle Deque eine Ausnahme.
        try{
            q.addFist();
            fail("addFirst() did not trow exception");
        }catch (DequeFull e){}
	}

	@After
	public void tearDown() {
		//removes all objects
        q.
	}
}
