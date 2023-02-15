package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static List<Integer> sort(List<Integer> lst) {
        return mergeSplit(lst);
    }

    private static List<Integer> mergeSplit(List<Integer> lst) {
        if (lst.size() <= 1) {
            return lst;
        }

        int mid = lst.size() / 2;

        List<Integer> left = mergeSplit(lst.subList(0, mid));
        List<Integer> right = mergeSplit(lst.subList(mid, lst.size()));

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
}
