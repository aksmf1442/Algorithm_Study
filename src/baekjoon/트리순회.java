package baekjoon;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 트리순회 {
    static Map<String, Node> nodes;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());

        nodes = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String[] input = sc.nextLine().split(" ");
            String node = input[0];
            String left = input[1];
            String right = input[2];
            nodes.put(node, new Node(node, left, right));
        }

        Node root = nodes.get("A");
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }

    private static void preOrder(Node node) {
        System.out.print(node.value);
        if (!node.left.equals(".")) {
            preOrder(nodes.get(node.left));
        }

        if (!node.right.equals(".")) {
            preOrder(nodes.get(node.right));
        }
    }

    private static void inOrder(Node node) {

        if (!node.left.equals(".")) {
            inOrder(nodes.get(node.left));
        }

        System.out.print(node.value);

        if (!node.right.equals(".")) {
            inOrder(nodes.get(node.right));
        }

    }

    private static void postOrder(Node node) {
        if (!node.left.equals(".")) {
            postOrder(nodes.get(node.left));
        }

        if (!node.right.equals(".")) {
            postOrder(nodes.get(node.right));
        }

        System.out.print(node.value);
    }



    static class Node {
        String value;
        String left;
        String right;

        public Node(String value, String left, String right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

}
