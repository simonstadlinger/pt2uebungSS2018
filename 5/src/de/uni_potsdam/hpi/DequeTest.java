package de.uni_potsdam.hpi;
import static org.junit.Assert.*;
import org.junit.*;

public abstract class DequeTest {

	@Before
	public abstract void setUp() throws Exception;
		//leere Deque
		//Deque mit 7 Elementen
		//volle Deque
	@Test
	public abstract void TestA()throws Exception;

	@Test
	public abstract void TestB()throws Exception;
		//Fügt man in eine Deque mit 7 Elementen eines mit addLast ein, erhält man es mit removeLast sofort zurück.
	@Test
	public abstract void TestC()throws Exception;
		//Fügt man in eine Deque mit 7 Elementen eines mit addFirst ein, erhält man es mit removeFirst sofort zurück.
	@Test
	public abstract void TestD()throws Exception;
		//Fügt man in eine Deque 6 mal den Wert null ein und danach ein von null verschiedenes Objekt o , und führt danach wiederholt removeFirst aus, so erhält man 6 mal den Wert null , und danach o .


	@Test
	public abstract void testE()throws Exception;
		//Nach Aufruf von clear liefert size den Wert 0.

	@Test
	public abstract void testF()throws Exception;
		//removeLast liefert für eine leere Deque eine Ausnahme.

	@Test
	public abstract void TestG()throws Exception;
		//addFirst liefert für eine volle Deque eine Ausnahme.

	@After
	public abstract void tearDown()throws Exception;
		//removes all objects
}
