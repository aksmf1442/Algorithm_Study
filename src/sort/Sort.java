package sort;

import java.util.ArrayList;
import java.util.List;

public class Sort {
    public static void main(String[] args) {

        List<Integer> lst = new ArrayList<>();
        lst.add(3);
        lst.add(2);
        lst.add(5);
        lst.add(3);
        lst.add(4);

        // 퀵정렬
//        List<Integer> sortedQuick = quickSort(lst);
//        System.out.println(sortedQuick);

        // 병합정렬
        List<Integer> sortedMerge = mergeSort(lst);
        System.out.println(sortedMerge);

    }

    private static List<Integer> mergeSort(List<Integer> lst) {
        if (lst.size() <= 1) {
            return lst;
        }

        int mid = lst.size() / 2;

        List<Integer> left = mergeSort(lst.subList(0, mid));
        List<Integer> right = mergeSort(lst.subList(mid, lst.size()));

        return merge(left, right);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        ArrayList<Integer> mergedList = new ArrayList<>();
        int l = 0;
        int r = 0;

        while(left.size() > l && right.size() > r){
            if (left.get(l) > right.get(r)) {
                mergedList.add(right.get(r));
                r += 1;
            } else {
                mergedList.add(left.get(l));
                l += 1;
            }
        }

        while(left.size() > l){
            mergedList.add(left.get(l));
            l += 1;
        }

        while(right.size() > r){
            mergedList.add(right.get(r));
            r += 1;
        }

        return mergedList;
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
