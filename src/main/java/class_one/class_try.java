package class_one;

public class class_try {
    public static void main(String[] args) {
        try {
            int[] mynum = {1, 2, 3};
            System.out.println(mynum[10]);
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
