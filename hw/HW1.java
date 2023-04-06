//todo: M114020002 & 呂心慈
//todo: write code in the chop(int p, int q) to calculate what happens when a tree branch is chopped. 
//todo: modify union(int p, int q) to maintain add nodes to the tree. 
//DO NOT EDIT other functions NOR add global variables.

public class HW1 {
 
	// ChopTrees is modified from QuickUnionUF https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/QuickUnionUF.java.html
	// QuickUnionUF JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/QuickUnionUF.html
	public static class ChopTrees {
		private int[] parent;   // parent[i] = parent of i
		private int count;      // number of components
		private int N;
		private boolean collapsed;

		/**
		* Initializes an empty union-find data structure with
		* {@code n} elements {@code 0} through {@code n-1}.
		* Initially, each element is in its own set.
		*
		* @param  n the number of elements
		* @throws IllegalArgumentException if {@code n < 0}
		*/
		public ChopTrees(int n) {
			count = n;
			N = n;
			collapsed = false;
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = 0;
			}
		}

		/**
		* Returns the number of sets.
		*
		* @return the number of sets (between {@code 1} and {@code n})
		*/
		public int count() {
			return count;
		}

		/**
		* Returns the canonical element of the set containing element {@code p}.
		*
		* @param  p an element
		* @return the canonical element of the set containing {@code p}
		* @throws IllegalArgumentException unless {@code 0 <= p < n}
		*/
		public int find(int p) {
			validate(p);
			while (parent[p] != 0 && parent[p] != -1)
				p = parent[p];
			return p;
		}

		/**
		* Returns true if the two elements are in the same set.
		* 
		* @param  p one element
		* @param  q the other element
		* @return {@code true} if {@code p} and {@code q} are in the same set;
		*         {@code false} otherwise
		* @throws IllegalArgumentException unless
		*         both {@code 0 <= p < n} and {@code 0 <= q < n}
		* @deprecated Replace with two calls to {@link #find(int)}.
		*/
		@Deprecated
		public boolean connected(int p, int q) {
			return find(p) == find(q);
		}

		// validate that p is a valid index
		private void validate(int p) {
			int n = parent.length;
			if (p < 0 || p >= n) {
				System.out.println("index " + p + " is not between 0 and " + (n-1));
			}
		}

		/**
		* Adds a branch to the tree, see assignment for details
		*
		* @param  p one element
		* @param  q the other element
		* @throws IllegalArgumentException unless
		*         both {@code 0 <= p < n} and {@code 0 <= q < n}
		*/
		public boolean union(int p, int q) {
			// modify code in this method
			// 將 p 連接到 q 下
			if(parent[p] == q || parent[q] == p) return false;
			parent[p] = q;
			count--;
			return true;
		}

		// // For chopping tree branches, see assignment for details
		public int chop(int p, int q){
			// write your code here
			if(collapsed) return -1;
			if(parent[p] == q || parent[q] == p){
				// p設為children,q設為parent
                if (parent[p] != q) {
                    int tmp;
                    tmp = p;
                    p = q;
                    q = tmp;
                }
				int countNodes = 0; // 計算砍掉幾個nodes
                int originTreeHight = 0; // 砍前樹高
                int chopTreeHight = 0; // 砍後樹高
                // 計算砍樹前的樹高
                for (int i = 0; i < parent.length; i++){
                    int count = 0;
                    int tmp=i;
                    while (parent[tmp] != 0 && parent[tmp] != -1){
                        tmp = parent[tmp];
                        count++;
                    }
                    if (count > originTreeHight) originTreeHight = count;
                }
                // 砍樹
                // 找出連接到p的所有小孩
                parent[p] = 0;// 先把 p 設為樹根，這樣就可以找到連到p的所有nodes
                for (int i = 0; i < parent.length; i++) {
                    if(find(i) == p) {
                        countNodes++;
                        parent[i] = -1;
                    } 
                }
                parent[p] = -1;
                // 計算砍後樹高
                for (int i = 0; i < parent.length; i++){
                    int count = 0;
                    int tmp=i;
                    while (parent[tmp] != 0 && parent[tmp] != -1){
                        tmp = parent[tmp];
                        count++;
                    }
                    if (count > chopTreeHight) chopTreeHight = count;
                }
                // 若砍後的樹高降低了就代表房子坍塌了
                if (originTreeHight > chopTreeHight) {
                    collapsed = true; 
                    return -1;
                }
                // 若沒有坍塌，則回傳砍掉的節點數
                return countNodes;
			}else{
				return -1;
			}
		}
	}

	public static void main(String[] args) {
		ChopTrees ct = new ChopTrees(25);
		ct.union(1, 2);
		ct.union(3, 4);
		ct.union(1, 3);
		ct.union(7, 2);
		ct.union(7, 3);
		ct.union(1, 6);
		ct.union(10, 11);
		ct.union(15, 12);
		ct.union(2, 17);
		ct.union(3, 15);
		ct.union(4, 11);
		ct.union(1, 3);
		ct.union(6, 8);
		ct.union(8, 19);
		ct.union(11, 17);
		ct.union(12, 15);
		ct.union(11, 18);
		ct.union(5, 14);

		System.out.println("After Chop 8, 6 => " + ct.chop(8, 6)); // expected output: After Chop 8, 6 => 1
		System.out.println("After Chop 11, 18 => " + ct.chop(11, 18));// expected output: After Chop 11, 18 => 3
		System.out.println("After Chop 1, 3 => " + ct.chop(1, 3));//  expected output: After Chop 1, 3 => 1

		System.out.println("Union of 20, 9 => " + ct.union(20,9)); // expected output: Union of 20, 9 => true

		System.out.println("After Chop 15, 3 => " + ct.chop(15, 3));//  expected output: After Chop 15, 3 => -1
		System.out.println("After Chop 14, 9 => " + ct.chop(14, 9));//  expected output: After Chop 14, 9 => -1

		System.out.println("Union of 20, 9 => " + ct.union(20,9)); // expected output: Union of 20, 9 => false
	}
}