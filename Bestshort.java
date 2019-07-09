package greedy;

public class Bestshort {
	
	static int Max = 1000;	//用来代表无此路径
	public static void main(String[] args) {
		int [][]a = {			//第一列全部补零，以便初始化
				{0,0,0,0,0,0,0},
				{0,0,3,4,Max,Max,Max},
				{0,Max,0,1,9,4,Max},
				{0,Max,Max,0,5,13,Max},
				{0,Max,Max,Max,0,Max,8},
				{0,Max,Max,Max,12,0,10},
				{0,Max,Max,Max,Max,Max,0}
		};
		int v = 1;
		int n = a.length;
		int []dist = new int[n];
		
		dijkstra(v,a,dist);	
		for(int i = 1;i<n;i++) {
			 System.out.println("从"+v+"出发到"+i+"的最短距离为："+dist[i]);
		}
	}
	
	public static int[] dijkstra(int v, int[][] a, int []dist) {
		//单元最短路径问题的Dijkstra算法
		int n = dist.length;						//顶点个数
		String[] path=new String[n];				 //存放从start到其他各点的最短路径的字符串表示	
		for(int i=1;i<n;i++)
            path[i]=new String(v+"-->"+i);
		
		if(v<1 || v>n) {
			System.out.println("源大小错误");
		}
		boolean []s = new boolean[n+1];				//标记当前该顶点的最短路径是否已经求出,true表示已求出
		//初始化
		for(int i = 1; i < n; i++) {
			dist[i] = a[v][i];						//dist[]都初始化为一开始1到其他边的距离
			s[i] = false;
		}
		dist[v] = 0;								//初始化源到源到最短路径为0
		s[v] = true;
		for(int i = 1;i < n-1; i++) {				//向顶点集加入n-1个顶点
			int temp = Max;
			int u = v;
			for(int j = 1; j< n; j++) {				//找出最小的dist[j]，并赋到源中
				if((!s[j])&&(dist[j]<temp)) {
					u = j;
					temp = dist[j];						
				}
			
			}
			System.out.println("此次添进顶点集的u="+u);
			s[u] = true;
			for(int j = 1; j< n; j++) {				//更新j赋到源之后，其他dist[j]的新最短特殊路径
				if((!s[j])&&(a[u][j] < Max)) {
					int newdist = dist[u] + a[u][j];
					if(newdist < dist[j]){			//保存dist[j]减小的情况
						dist[j] = newdist;
						path[j] = path[u]+"-->"+j;
					}
				}
			}
				
		}
		//输出路径
		for(int j= 1;j < n; j++) {
			System.out.println("从1出发到"+j+"的最短路径为:"+path[j]);
		}
		System.out.println("============================");
		return dist;
	}

}
