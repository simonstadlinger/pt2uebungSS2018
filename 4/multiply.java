class multiply{
    static final int N = 10; // default value for the array sizes

    // Generate an matrix F of NxN elements
    // initialize it according to one of the following methods
    // 0 - F[i][j] = 0.0
    // 1 - F[i][j] = i+j
    // 2 - F[i][j] = i-j
    // 3 - F[i][j] = random value between -10 and 10
    static double[][] init(int method)
    {
	double[][] result = new double[N][N];
	if(method == 0)
	    // nothing to do: values are zero-initialized already
	    return result;
	// If we initialize with random numbers, create a PRNG
	java.util.Random r = null;
	if (method == 3)
	    r = new java.util.Random();
	// iterate over each element and initialize it
	int i,k;
	for (i=0; i<N; i++)
	    for (k=0; k<N; k++)
		switch(method) {
		case 1:
		    result[i][k] = i+k;
		    break;
		case 2:
		    result[i][k] = i-k;
		    break;
		case 3:
		    // nextDouble returns a number between 0.0 and 1.0
		    result[i][k] = r.nextDouble()*20.0-10.0;
		    break;
		}
	return result;
    }

    // Print a matrix to System.out
    static void print(double[][] m)
    {
	int i,k;
	java.text.DecimalFormat d = new java.text.DecimalFormat("###0.0");
	for (i=0; i<N; i++) {
	    for (k=0; k<N; k++) {
		String text = d.format(m[i][k]);
		for(int sp=0;sp<7-text.length();sp++)
		    System.out.print(" ");
		System.out.print(text);
	    }
	    System.out.println();
	}
    }

    // Multiply C = A*B
    static void mult(double[][] C, double[][] A, double[][] B)
    {
	int i,k,m;
	for (i=0; i<N; i++)
	    for (k=0; k<N; k++) {
		C[i][k] = 0.0;
		for (m=0; m<N; m++)
		    C[i][k] += A[i][m]*B[m][k];
	    }
    }

    public static void main(String args[])
    {
	// initialize three matrices
	double[][] A,B,C;
	A = init(1);
	B = init(2);
	C = init(0);
	// multiply them
	mult(C, A, B);
	// print them out
	System.out.println("========== A ============");
	print(A);
	System.out.println("========== B ============");
	print(B);
	System.out.println("========== C ============");
	print(C);
    }
}
