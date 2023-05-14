package 복습;

import java.util.*;

public class 다단계칫솔판매복습 {

    public static void main(String[] args) {
        다단계칫솔판매_Solution solution = new 다단계칫솔판매_Solution();
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};
        int[] result = solution.solution(enroll, referral, seller, amount);
        for (int i : result) {
            System.out.print(i + ",");
        }
    }
}

class 다단계칫솔판매_Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Arrays.fill(answer, 0);

        // 노드 초기화
        Map<String, Nodee> nodeMap = new HashMap();
        nodeMap.put("-", new Nodee("-"));
        for (int i = 0; i < enroll.length; i++) {
            String name = enroll[i];
            nodeMap.put(name, new Nodee(name));
        }

        // 부모, 자식 노드 연결
        for (int i = 0; i < enroll.length; i++) {
            Nodee childNode = nodeMap.get(enroll[i]);
            Nodee parentNode = nodeMap.get(referral[i]);
            childNode.parent = parentNode;
            parentNode.child.add(childNode);
        }

        // 계산
        for (int i = 0; i < seller.length; i++) {
            Nodee node = nodeMap.get(seller[i]);
            int profit = amount[i] * 100;
            shareProfit(node, profit);
        }

        // 결과 도출
        for (int i = 0; i < enroll.length; i++) {
            Nodee node = nodeMap.get(enroll[i]);
            answer[i] = node.money;
        }

        return answer;
    }

    private void shareProfit(Nodee node, int profit) {
        int parentProfit = profit / 10;
        int currentProfit = profit - parentProfit;

        // 부모 노드에게 가는 수익이 1원 미만이면 주지 않음.
        if (parentProfit < 1) {
            node.money += profit;
            return;
        }

        node.money += currentProfit;
        if (node.parent != null) {
            shareProfit(node.parent, parentProfit);
        }
    }
}

class Nodee {
    String name;
    Nodee parent;
    List<Nodee> child;
    int money;

    public Nodee(String name) {
        this.name = name;
        this.parent = null;
        this.child = new ArrayList<>();
        this.money = 0;
    }

}

