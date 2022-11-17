package level2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class 롤케이크자르기 {

    public static void main(String[] args) {
        롤케이크자르기_Solution solution = new 롤케이크자르기_Solution();
        int[] topping = {1, 2, 1, 3, 1, 4, 1, 2};
        System.out.println(solution.solution(topping));
    }
}

class 롤케이크자르기_Solution {

    public int solution(int[] topping) {
        int result = 0;

        List<Integer> toppings = Arrays.stream(topping).boxed().collect(Collectors.toList());
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for (int tp : toppings) {
            map2.put(tp, map2.getOrDefault(tp, 0) + 1);
        }

        for (int tp : toppings) {
            map1.put(tp, 1);
            map2.put(tp, map2.get(tp) - 1);
            if (map2.get(tp) == 0) {
                map2.remove(tp);
            }
            if (map1.size() == map2.size()) {
                result++;
            }
        }

        return result;
    }
}
