package sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {

    public static List<Integer> sort(List<Integer> lst) {
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
        result.addAll(sort(left));
        result.addAll(mid);
        result.addAll(sort(right));
        return result;
    }
}
