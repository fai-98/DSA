import java.io.*;
import java.util.*;

public class binary_tree {


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

    for (int i = 1; i < arr.length; i++) {
      Pair tos = st.peek();
      if (arr[i] == null) {
        tos.state++;
        if (tos.state == 2)st.pop();
      } else {
        Node nn = new Node(arr[i], null, null);
        if (tos.state == 0) {
          tos.n.left = nn;
          tos.state++;
        } else if (tos.state == 1) {
          tos.n.right = nn;
          tos.state++;
          st.pop();
        }
        st.push(new Pair(nn, 0));
      }
    }

    return root;
  }

  public static void display(Node node) {
    if (node == null)return;
    String ld = "";
    String rd = "";
    ld += node.left == null ? "." : node.left.data;
    rd += node.right == null ? "." : node.right.data;
    System.out.println(ld + "<- " + node.data + " ->" + rd);
    display(node.left);
    display(node.right);
  }

  public static void main(String[] args) {
    Integer[] arr = {10, 20, 40, null, null, 50, 80, null, null, null, 30, 60, null, 90, null, null, 70, null, null};
    Node root = construct(arr);
    display(root);
  }

}


//state =0  means left child push (if not null); state++;
//state =1  means right child push (if not null); state++; now state = 2 so pop , then push right ch
//state= 2 means l , r done , pop();
//if arr[i]==null , just tos.state++ don't do anything


