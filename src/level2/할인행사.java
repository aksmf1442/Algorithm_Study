package level2;

import java.util.HashMap;
import java.util.Map;

public class 할인행사 {
    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"};
        할인행사_Solution solution = new 할인행사_Solution();
        System.out.println(solution.solution(want, number, discount));
    }
}

class 할인행사_Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> foods = new HashMap<>();

        int result = 0;
        for (int i = 0; i <= discount.length - 10; i++) {
            initFoods(foods, want, number);

            boolean isPossible = true;
            for (int j = 0; j < 10; j++) {
                String key = discount[i + j];
                Integer food = foods.get(key);
                if (food == null || food == 0) {
                    isPossible = false;
                    break;
                }
                foods.put(key, food - 1);
            }

            if (isPossible) {
                result++;
            }
        }

        return result;
    }

    private void initFoods(Map<String, Integer> foods, String[] want, int[] number) {
        for (int i = 0; i < want.length; i++) {
            foods.put(want[i], number[i]);
        }
    }
}