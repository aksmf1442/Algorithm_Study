package sort;

import java.util.ArrayList;
import java.util.List;

public class Sort {
    public static void main(String[] args) {

        List<Integer> lst = new ArrayList<>();
        lst.add(31);
        lst.add(2);
        lst.add(5);
        lst.add(3);
        lst.add(4);

        // 퀵정렬
//        List<Integer> result = QuickSort.sort(lst);

        // 병합정렬
//        List<Integer> result = MergeSort.sort(lst);

        // 기수정렬
        List<Integer> result = RadixSort.sort(lst);

        System.out.println(result);

    }




}
