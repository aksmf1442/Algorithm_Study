package level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 다단계칫솔판매 {

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

/**
 * goal:
 */

/**
 * 알아야 할 것
 * 1. 모든 판매원은 칫솔의 판매에 의하여 발생하는 이익에서 10%를 자신을 조직에 참여시킨 부모 노드에게 배분하고 나머지는 가짐
 * 2. 그렇다면 반대의 경우를 생각하면 부모 노드는 자식 노드 수익의 10%를 먹을 수 있다는 것을 알 수 있음
 * 3. 10%를 계산할 때는 원 단위에서 끊음
 * 4. 10%를 계산한 금액이 1원 미만인 경우는 그냥 자신이 가짐(번 돈이 10원 미만일 경우)
 */

/**
 * 풀이 방법
 * 1. dfs로 풀면 풀 수 있을 듯.
 */

class 다단계칫솔판매_Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Arrays.fill(answer, 0);

        // 노드 초기화
        Map<String, Node> nodeMap = new HashMap();
        nodeMap.put("-", new Node("-"));
        for (int i = 0; i < enroll.length; i++) {
            String name = enroll[i];
            nodeMap.put(name, new Node(name));
        }

        // 부모, 자식 노드 연결
        for (int i = 0; i < enroll.length; i++) {
            Node childNode = nodeMap.get(enroll[i]);
            Node parentNode = nodeMap.get(referral[i]);
            childNode.parent = parentNode;
            parentNode.child.add(childNode);
        }

        // 계산
        for (int i = 0; i < seller.length; i++) {
            Node node = nodeMap.get(seller[i]);
            int profit = amount[i] * 100;
            shareProfit(node, profit);
        }

        // 결과 도출
        for (int i = 0; i < enroll.length; i++) {
            Node node = nodeMap.get(enroll[i]);
            answer[i] = node.money;
        }

        return answer;
    }

    private void shareProfit(Node node, int profit) {
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

class Node {
    String name;
    Node parent;
    List<Node> child;
    int money;

    public Node(String name) {
        this.name = name;
        this.parent = null;
        this.child = new ArrayList<>();
        this.money = 0;
    }

}

