public class Main {

    public static void main(String[] args) {
        MyFunction2 f = (a,b) -> a > b ? a : b;

        int value = f.max(3, 5);
        System.out.println("value = " + value);
    }
}