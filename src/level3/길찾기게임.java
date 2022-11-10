package level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 길찾기게임 {

    public static void main(String[] args) {
        길찾기게임_Solution solution = new 길찾기게임_Solution();
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2},
            {2, 2}};
        int[][] result = solution.solution(nodeinfo);
        for (int i = 0; i < result.length; i++) {
            for (int j : result[i]) {
                System.out.print(j + ", ");
            }
            System.out.println();
        }
    }
}


/**
 * goal: 트리를 만들어서 전위순회, 후위순회 시키기.
 */

class 길찾기게임_Solution {

    public static ArrayList<Node> nodeList = new ArrayList<>();
    public static int index = 0;

    public int[][] solution(int[][] nodeinfo) {
        // node 생성
        for(int i = 0; i < nodeinfo.length; i++) {
            nodeList.add(new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        // y 기준 내림차순 정렬
        Collections.sort(nodeList);

        // 루트 노드
        Node root = nodeList.get(0);

        // 노드 연결
        for(int i = 1; i < nodeList.size(); i++) {
            Node child = nodeList.get(i);
            connectNode(root, child);
        }

        int[][] answer = new int[2][nodeList.size()];

        // 전위 순회
        preOrder(answer, root);
        index = 0;

        // 후위 순회
        postOrder(answer, root);

        return answer;
    }

    // 노드 연결
    public static void connectNode(Node parent, Node child) {
        if(child.x < parent.x) {
            if(parent.left == null) {
                parent.left = child;
            } else {
                connectNode(parent.left, child);
            }
        } else {
            if(parent.right == null) {
                parent.right = child;
            } else {
                connectNode(parent.right, child);
            }
        }
    }

    // 전위 순회
    public static void preOrder(int[][] arr, Node node) {
        if(node != null) {
            arr[0][index++] = node.data;
            if(node.left != null) preOrder(arr, node.left);
            if(node.right != null) preOrder(arr, node.right);
        }
    }

    // 후위 순회
    public static void postOrder(int[][] arr, Node node) {
        if(node != null) {
            if(node.left != null) postOrder(arr, node.left);
            if(node.right != null) postOrder(arr, node.right);
            arr[1][index++] = node.data;
        }
    }

    class Node implements Comparable<Node>{
        int data;
        int x;
        int y;
        Node left;
        Node right;


        Node(int data, int x, int y){
            this.data = data;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node n) {
            return n.y - this.y;
        }
    }
}
