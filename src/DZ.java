public class DZ {

    /**
     * Метод, возвращающий количество чётных элементов массива. countEvens([2, 1, 2, 3, 4]) → 3 countEvens([2, 2, 0]) → 3 countEvens([1, 3, 5]) → 0
     */
    public static int countEven(int[] array) {
        int cnt = 0;
        for (int a : array) {
            if (a % 2 == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    /**
     * Функция, возвращающая разницу между самым большим и самым маленьким элементами переданного не пустого массива.
     */

    public static int printDifference(int[] arr) {
        int maxInt, minInt, result;
        boolean flag;
        try {
            do {
                flag = false;
                for (int i = 0; i < arr.length - 1; i++) {
                    if (arr[i] > arr[i + 1]) {
                        int temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        flag = true;
                    }
                }
            } while (flag);
            maxInt = arr[arr.length - 1];
            minInt = arr[0];
            result = maxInt - minInt;
            System.out.print("Разница между самым большим и самым маленьким числом массива равна: ");
            return result;
        } catch (NullPointerException | ArrayIndexOutOfBoundsException an) {
            System.out.println("Ожидался не пустой массив");
            System.out.print("Вы передали массив длиной: ");
            return 0;
        }
    }

    /**
     * Функция, возвращающая истину, если в переданном массиве есть два соседних элемента, с нулевым значением.
     */
    public static boolean getTrue(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            if ((arr[i - 1] == 0) && (arr[i + 1] == 0))
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        int[] arr = {};
        System.out.println(printDifference(arr));
    }
}



