package ejercicio1;

public class MultipathExample {

    public int multiPath1(int a, int b, int c) {
        if (a > 5) {
            c += a;
        }
        if (b > 5) {
            c += b;
        }
        return c;
    }

    public int multiPath2(int a, int b, int c) {
        if ((a > 5) && (b < 5)) {
            b += a;
        }
        if (c > 5) {
            c += b;
        }
        return c;
    }

    public int multiPath3(int a, int b, int c) {
        if ((a > 5) & (b < 5)) {
            b += a;
        }
        if (c > 5) {
            c += b;
        }
        return c;
    }
}
