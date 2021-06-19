import java.io.*;
import java.util.*;

public class BinaryTreeConstruct {


  public static class Node {
    int data;
    Node left;
    Node right;

    public Node(int data, Node left , Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public static class Pair {
    Node n;
    int state;

    public Pair(Node node, int state) {
      this.state = state;
      this.n = node;
    }
  }


  public static Node construct(Integer[] arr) {
    Node root = new Node(arr[0], null, null);
    Stack<Pair> st = new Stack<>();
    st.push(new Pair(root, 0));

    for (int i = 0; i < arr.length; i++) {
      Pair tos = st.peek();
      if (arr[i] == null) {
        tos.state++;
        if (tos.state == 2) {
          st.pop();
        }
      } else {
        Node nn = new Node(arr[i], null, null);
        if (tos.state == 1) {
          tos.n.right = nn;
          st.pop();
        }
        st.push(new Pair(nn, 0));
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null)return ;

    String str = "";
    str += node.left == null ? "." : node.left.data;
    str += " <- " + node.data + " -> ";
    str += node.right == null ? "." : node.right.data;
    System.out.println(str);

    display(node.left);
    display(node.right);
  }

  public static void main(String[] args) {
    Integer[] arr = {10, 20, 40, null, null, 50, 80, null, null, null, 30, 60, null, 90, null, null, 70, null, null};
    Node root = construct(arr);
    display(root);
  }

}