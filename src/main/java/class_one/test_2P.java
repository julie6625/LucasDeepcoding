package class_one;

public class test_2P {
    public static void main(String[] args) {
        int i = 0;
        int j = 1;
        int a = 0;
        int amount = 1;

        while (amount <= 7){
            System.out.println(i);
            a = i;
            i = j;
            j += a;
            amount ++;
        }
    }
}
