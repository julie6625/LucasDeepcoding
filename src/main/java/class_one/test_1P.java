package class_one;

public class test_1P {
    public static void main(String[] args) {
        int[] IDS = {2, 5, 8, 9};
        for (int ID : IDS){
            //int ID : IDS 常用於for迴圈裡，可以代表array的每一個值
            if (ID%3 != 2){
                System.out.print(ID+", ");
            }
        }
    }
}
