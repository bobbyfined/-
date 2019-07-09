package opackage;

public class Opackage {
	public static void knapsack(int[] v, int[] w, int c, int[][] m)
	{
		int n = v.length - 1;
		int jMax = Math.min(w[n], c);
		//��ʼ������ m
		for(int j = 0; j <= jMax; j++)
		{
			m[n][j] = 0;
		}
		//ֻ����Ʒ n ʱ�������Ʒ n ������С�ڱ������������򱳰��з�����Ʒ n
		for(int j = w[n]; j <= c; j++)	
		{
			m[n][j] = v[n];
		}
		//�����������������½���򱳰��������Ʒ n-1, n-3 ... 1
		for(int i = n - 1; i >= 0; i--)
		{
			jMax = Math.min(w[i], c);	
			//����������Ϊ j ʱ����¼��Ʒ i+1��n��
			for(int j = 0; j <= jMax; j++)
			{
				m[i][j] = m[i + 1][j];
			}
			//���������Ʒ i ������ֵΪ m[i + 1][j - w[i]] + v[i] , �����������Ʒ i �����ֵΪ m[i + 1][j]
			for(int j = w[i]; j <= c; j++)
			{
				m[i][j] = Math.max(m[i + 1][j], m[i + 1][j - w[i]] + v[i]);
			}				
			m[0][c] = m[1][c];
			if(c >= w[1])
			{
				m[0][c] = Math.max(m[0][c], m[1][c - w[0]] + v[0]);
			}
		}
	}
	
	//�������Ž⣬�ж��Ƿ��򱳰�������Ʒ i
	public static void traceback(int[][] m, int[] w, int c, int[] x)
	{
		int n = w.length - 1;
		for(int i = 0; i < n; i++)
		{
			//���û���򱳰��з�����Ʒ���� x[i] ��Ϊ 0
			if(m[i][c] == m[i + 1][c])
			{
				x[i] = 0;
			}
			else //�򱳰��з�������Ʒ��x[i] ��Ϊ 1
			{
				x[i] = 1;
				c -= w[i];	//������ʣ������
			}
		}
		x[n] = (m[n][c] > 0) ? 1 : 0;	//�ж����һ����Ʒ��û�з��뱳����
	}
	
	public static void main(String[] args)
	{
		int[] v = {20, 6, 8, 15, 18};
		int[] w = {5, 4, 8, 6, 9};		 
		int c = 18;
		int[][] m = new int[w.length][c + 1];
		int[] x = new int[w.length];
		
		knapsack(v, w, c, m);
		traceback(m, w, c, x);
		
		for(int i : x)
			System.out.print(i + "	");
		System.out.println();
		
		for(int[] i : m)
		{
			for(int j : i)
				System.out.print(j + "	");
			System.out.println();
		}
	}
}
