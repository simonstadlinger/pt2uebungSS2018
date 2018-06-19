import static org.junit.Assert.*
import org.junit.*

public class DequeTest {

	@Before
	public void setUp() {
		//leere Deque

		//Deque mit 7 Elementen

		//volle Deque
	}

	@Test
	public void testA() {
		//Für eine leere Deque q gilt nach q.addLast(o) : o == q.removeFirst()
	}

	@Test
	public void testB() {
		//Fügt man in eine Deque mit 7 Elementen eines mit addLast ein, erhält man es mit removeLast sofort zurück.
	}

	@Test
	public void testC() {
		//Fügt man in eine Deque mit 7 Elementen eines mit addFirst ein, erhält man es mit removeFirst sofort zurück.
	}

	@Test
	public void testD() {
		//Fügt man in eine Deque 6 mal den Wert null ein und danach ein von null verschiedenes Objekt o , und führt danach wiederholt removeFirst aus, so erhält man 6 mal den Wert null , und danach o .
	}

	@Test
	public void testE() {
		//Nach Aufruf von clear liefert size den Wert 0.
	}

	@Test
	public void testF() {
		//removeLast liefert für eine leere Deque eine Ausnahme.
	}

	@Test
	public void TestG() {
		//addFirst liefert für eine volle Deque eine Ausnahme.
	}

	@After
	public void tearDown() {
		//removes all objects
	}
}