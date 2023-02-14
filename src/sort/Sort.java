package sort;

import java.util.ArrayList;
import java.util.List;

public class Sort {
    public static void main(String[] args) {

        // 퀵정렬
        List<Integer> quick = new ArrayList<>();
        quick.add(3);
        quick.add(2);
        quick.add(5);
        quick.add(4);
        List<Integer> sortedQuick = quickSort(quick);
        System.out.println(sortedQuick);

    }

    public static List<Integer> quickSort(List<Integer> lst) {
        if (lst.size() <= 1) {
            return lst;
        }

        int pivot = lst.get(lst.size() / 2);
        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (Integer value: lst) {
            if (value == pivot) {
                mid.add(value);
                continue;
            }

            if (value < pivot) {
                left.add(value);
                continue;
            }

            if (value > pivot) {
                right.add(value);
            }
        }

        List<Integer> result = new ArrayList<>();
        result.addAll(quickSort(left));
        result.addAll(mid);
        result.addAll(quickSort(right));
        return result;
    }
}
