import de.uni_potsdam.hpi.*;

public class DequePerformance {
	public DequePerformance() {}

	static final int N = 100000000;

	public static void main(String[] args) {
		LinkedDeque linked = new LinkedDeque(1000);
		ArrayDeque  array  = new ArrayDeque(1000);

		for (int i = 0; i < 500; i++) {
			try {
				linked.addFirst(new Object());
				array.addFirst(new Object());
			} catch (DequeFull ex) {
				System.exit(1);
			}				
		}

		long linkedStart = System.nanoTime();
		for (int i = 0; i < N; i++) {
			try {
				linked.removeLast();
				linked.addLast(" ");
			} catch (Exception ex) {
				System.exit(1);
			}	
		}
		long linkedEnd   = System.nanoTime();
		
		long arrayStart  = System.nanoTime();
		for (int i = 0; i < N; i++) {
			try {
				array.removeLast();
				array.addLast(" ");
			} catch (Exception ex) {
				System.exit(1);
			}
		}
		long arrayEnd    = System.nanoTime();

		System.out.println("N = " + N);
		System.out.println("ArrayDeque:  " + (arrayEnd - arrayStart));
		System.out.println("LinkedDeque: " + (linkedEnd - linkedStart));
			

	}
}