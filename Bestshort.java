package greedy;

public class Bestshort {
	
	static int Max = 1000;	//���������޴�·��
	public static void main(String[] args) {
		int [][]a = {			//��һ��ȫ�����㣬�Ա��ʼ��
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
			 System.out.println("��"+v+"������"+i+"����̾���Ϊ��"+dist[i]);
		}
	}
	
	public static int[] dijkstra(int v, int[][] a, int []dist) {
		//��Ԫ���·�������Dijkstra�㷨
		int n = dist.length;						//�������
		String[] path=new String[n];				 //��Ŵ�start��������������·�����ַ�����ʾ	
		for(int i=1;i<n;i++)
            path[i]=new String(v+"-->"+i);
		
		if(v<1 || v>n) {
			System.out.println("Դ��С����");
		}
		boolean []s = new boolean[n+1];				//��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,true��ʾ�����
		//��ʼ��
		for(int i = 1; i < n; i++) {
			dist[i] = a[v][i];						//dist[]����ʼ��Ϊһ��ʼ1�������ߵľ���
			s[i] = false;
		}
		dist[v] = 0;								//��ʼ��Դ��Դ�����·��Ϊ0
		s[v] = true;
		for(int i = 1;i < n-1; i++) {				//�򶥵㼯����n-1������
			int temp = Max;
			int u = v;
			for(int j = 1; j< n; j++) {				//�ҳ���С��dist[j]��������Դ��
				if((!s[j])&&(dist[j]<temp)) {
					u = j;
					temp = dist[j];						
				}
			
			}
			System.out.println("�˴�������㼯��u="+u);
			s[u] = true;
			for(int j = 1; j< n; j++) {				//����j����Դ֮������dist[j]�����������·��
				if((!s[j])&&(a[u][j] < Max)) {
					int newdist = dist[u] + a[u][j];
					if(newdist < dist[j]){			//����dist[j]��С�����
						dist[j] = newdist;
						path[j] = path[u]+"-->"+j;
					}
				}
			}
				
		}
		//���·��
		for(int j= 1;j < n; j++) {
			System.out.println("��1������"+j+"�����·��Ϊ:"+path[j]);
		}
		System.out.println("============================");
		return dist;
	}

}
