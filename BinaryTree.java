
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class BinaryTree {

   private TreeNode root;

   private class TreeNode {
      private TreeNode left;
      private TreeNode right;
      private TreeNode children[] = new TreeNode[9];
      //Class_Name obj[ ]= new Class_Name[Array_Length];
      
      //Data 
      private int data; 						// Can be any generic type
      private char data_c; 						// X or O for my immediate childres 
      private String data_S; 
      private int childrenCount;
      private int childrenMaxScore;  			// Number of Wins
      private int childrenMinScore;  			// Number of Loses
      private int childrenMinimalHeightWin;		// How fast can you (data_c) win? 
      private int childrenMinimalHeightLose;	// How fast can you (data_c) win?
      											// terminal node win, lose, tie, collision
      public TreeNode(int data) {
         this.data = data;
      }
      
      public TreeNode(char data_c) {
          this.data_c = data_c;
       }

      public TreeNode(String data_S) {
          this.data_S = data_S;
       }

      public TreeNode(int data, char data_c, String data_S) {
          this.data_S = data_S;
          this.data_c = data_c;
          this.data = data;
       }
   }

   public void preOrder(TreeNode root) {
      if (root == null) {
         return;
      }

      System.out.print(root.data + " ");
      preOrder(root.left);
      preOrder(root.right);
   }

   public void preOrder() {
      if (root == null) {
         return;
      }

      Stack<TreeNode> stack = new Stack<>();
      stack.push(root);

      while (!stack.isEmpty()) {
         TreeNode temp = stack.pop();
         System.out.print(temp.data + " ");
         if (temp.right != null) {
            stack.push(temp.right);
         }
         if (temp.left != null) {
            stack.push(temp.left);
         }
      }
   }

   public void inOrder(TreeNode root) {
      if (root == null) {
         return;
      }

      inOrder(root.left);
      System.out.print(root.data + " ");
      inOrder(root.right);
   }

   public void inOrder() {
      if (root == null) {
         return;
      }

      Stack<TreeNode> stack = new Stack<>();
      TreeNode temp = root;

      while (!stack.isEmpty() || temp != null) {
         if (temp != null) {
            stack.push(temp);
            temp = temp.left;
         } else {
            temp = stack.pop();
            System.out.print(temp.data + " ");
            temp = temp.right;
         }
      }
   }

   public void postOrder(TreeNode root) {
      if (root == null) {
         return;
      }

      postOrder(root.left);
      postOrder(root.right);
      System.out.print(root.data + " ");
   }

   public void postOrder() {
      TreeNode current = root;
      Stack<TreeNode> stack = new Stack<>();

      while (current != null || !stack.isEmpty()) {
         if (current != null) {
            stack.push(current);
            current = current.left;
         } else {
            TreeNode temp = stack.peek().right;
            if (temp == null) {
               temp = stack.pop();
               System.out.print(temp.data + " ");
               while (!stack.isEmpty() && temp == stack.peek().right) {
                  temp = stack.pop();
                  System.out.print(temp.data + " ");
               }
            } else {
               current = temp;
            }
         }
      }
   }

   public void levelOrder() {
      if (root == null) {
         return;
      }

      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);

      while (!queue.isEmpty()) {
         TreeNode temp = queue.poll();
         System.out.print(temp.data + " ");
         if (temp.left != null) {
            queue.offer(temp.left);
         }
         if (temp.right != null) {
            queue.offer(temp.right);
         }
      }
   }

   public int findMax() {
      return findMax(root);
   }

   public int findMax(TreeNode root) {
      if (root == null) {
         return Integer.MIN_VALUE;
      }

      int result = root.data;
      int left = findMax(root.left);
      int right = findMax(root.right);

      if (left > result) {
         result = left;
      }

      if (right > result) {
         result = right;
      }

      return result;

   }

   public void createBinaryTree() {
      TreeNode first = new TreeNode(1);
      TreeNode second = new TreeNode(2);
      TreeNode third = new TreeNode(3);
      TreeNode fourth = new TreeNode(4);
      TreeNode fifth = new TreeNode(5);
      TreeNode sixth = new TreeNode(6);
      TreeNode seventh = new TreeNode(7);

      root = first;
      first.left = second;
      first.right = third;
      first.children[0] =second;
      first.children[1] =third;
      
      second.left = fourth;
      second.right = fifth;
      second.children[0] =fourth;
      second.children[1] =fifth;
      
      third.left = sixth;
      third.right = seventh;
      third.children[0] =sixth;
      third.children[1] =seventh;
      
   }

   public static Set<String> permutationFinder(String str) {
       Set<String> perm = new HashSet<String>();
       //Handling error scenarios
       
       if (str == null) {
           return null;
           // Exit condition
       } else if (str.length() == 0) {
           perm.add("");
           return perm;
           // Exit condition 
       }
       char initial = str.charAt(0); // first character
       String rem = str.substring(1); // Full string without first character
       
       Set<String> words = permutationFinder(rem); //recursion 
       
       for (String strNew : words) {
           for (int i = 0;i<=strNew.length();i++){
               //perm.add(charInsert(strNew, initial, i));
           	perm.add( strNew.substring(0, i) + initial + strNew.substring(i) );
           }
       }
       return perm;
   }

   public static Integer numberOfDifferences(String str1, String str2) {
	    if (str1 == null) {
	        return 0;
	    }
	    if (str2 == null) {
	        return str1.length();
	    }
	    Integer minLength;
	    // designed when both strings are the exact same length 
	    if (str1.length()<str2.length())
	    	minLength = str1.length();
	    else
	    	minLength = str2.length();
	    Integer nOfDifferences = 0;
	    for (int i =0; i<minLength; i++)
	    {
	    	if (str1.charAt(i) != str2.charAt(i) )
				nOfDifferences++;
	    }		
	    return nOfDifferences;
	}
   
   public static String difference(String str1, String str2) {
	    if (str1 == null) {
	        return str2;
	    }
	    if (str2 == null) {
	        return str1;
	    }
	    int at = indexOfDifference(str1, str2);
	    if (at == -1) {
	        return "";
	    }
	    return str2.substring(at);
	}

	public static int indexOfDifference(CharSequence cs1, CharSequence cs2) {
	    if (cs1 == cs2) {
	        return -1;
	    }
	    if (cs1 == null || cs2 == null) {
	        return 0;
	    }
	    int i;
	    for (i = 0; i < cs1.length() && i < cs2.length(); ++i) {
	        if (cs1.charAt(i) != cs2.charAt(i)) {
	            break;
	        }
	    }
	    if (i < cs2.length() || i < cs1.length()) {
	        return i;
	    }
	    return -1;
	}
	
   public void createTicTacToeTree() {
	      String ParentPattern = "---------";
	   	  TreeNode first = new TreeNode(ParentPattern);
	      TreeNode second = new TreeNode(2);
	      TreeNode third = new TreeNode(3);
	      TreeNode fourth = new TreeNode(4);
	      TreeNode fifth = new TreeNode(5);
	      TreeNode sixth = new TreeNode(6);
	      TreeNode seventh = new TreeNode(7);

	      root = first;
	      String EntireString = "";
          ArrayList<String> arrayFocusOnOneParent = new ArrayList<String>();
          ArrayList<String> arrayOnlyValidChildren = new ArrayList<String>();
          ArrayList<String> arrayAllPermutations = new ArrayList<String>();
          
          for (int ii=0 ; ii<9 ; ii++)
          {
        	  String sX = "X";
        	  String sO = "O";
        	  String sH = "-";
        	  EntireString.replace("-", "");
        	  EntireString = EntireString +  sX.repeat((ii+1)%2) + sO.repeat(ii%2) ;
        	  String EntireStringMinus =   sH.repeat(9-(EntireString.length()));
     
        	  System.out.println("***** Find all permutations of " + EntireString+EntireStringMinus );
        	  
              Set<String> sSet2 = permutationFinder(EntireString+EntireStringMinus);
              String sPerms2 = sSet2.toString();
              sPerms2 = sPerms2.replace("[", "{\"");
              sPerms2 = sPerms2.replace("]", "\"}");
              sPerms2 = sPerms2.replace(",", "\",\"");
              sPerms2 = sPerms2.replace(" ", "");

              sPerms2 = sPerms2.replace(" ", "");
              sPerms2 = sPerms2.replace("{", "");
              sPerms2 = sPerms2.replace("}", "");
              sPerms2 = sPerms2.replace("\"", "");
              
              String[] array = sPerms2.split(",");

              //UNSORTED 
              if (ii<=9 )
              {
            	  // NOT SORTED IN LIST FORMAT
            	  //System.out.println(sPerms2);
              }
              //SORTED
              Arrays.sort(array);
             
              // ii=0 --> One X--------  	  
	          for(String value:array) {
	              //System.out.print(value + " ");
	        	  arrayAllPermutations.add(value);
	              TreeNode childNode = new TreeNode(value);
	              //root.children[numberOfDifferences(ParentPattern, value)] = childNode;
	              
	              if (numberOfDifferences(ParentPattern, value) ==1 ) 
	              {
	            	  arrayFocusOnOneParent.add(value);
	            	  if (ii < 5) {
	            		  arrayOnlyValidChildren.add(value);
	            	  }
	            	  if (ii>=5) { // 5,6,7,8
	            		  
	            	  }
	              }
	              if (value.equals(array[array.length-1]))
	            	  ParentPattern = value;
	           }
	          System.out.println("\narray.length=" + array.length +"\n");
 
   	          for(String value2:arrayFocusOnOneParent) {
	              System.out.print(value2 + " ");
	           }
   	          
	          System.out.println("\n arrayFocusOnOneParent.length =" + arrayFocusOnOneParent.size() +"\n");
          }

	      
	      first.children[0] =second;
	      first.children[1] =third;
	      
	      second.left = fourth;
	      second.right = fifth;
	      second.children[0] =fourth;
	      second.children[1] =fifth;
	      
	      third.left = sixth;
	      third.right = seventh;
	      third.children[0] =sixth;
	      third.children[1] =seventh;
	      
	   }
   
   public static void main(String[] args) {
      BinaryTree bt = new BinaryTree();
      bt.createBinaryTree();
      bt.postOrder();
      
      System.out.println();
      
      BinaryTree bt2 = new BinaryTree();
      bt2.createTicTacToeTree();
      bt2.postOrder();

      char[] chars2 = new char[9];
      Arrays.fill(chars2, 'X');
      String s2 = new String(chars2);

      String EntireString = "";
      System.out.println("");
      
      //System.out.println("difference=" + 		difference         ("XO-------", "XO------X"));
      //System.out.println("indexOfDifference" + 	indexOfDifference  ("XO-------", "XO------X"));
      //System.out.println("numberOfDifferences"+	numberOfDifferences("XO-------", "XO------X"));
      
   }
}