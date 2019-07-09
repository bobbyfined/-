package opackage;

public class Ppackage {
	public static void main(String[] args) {
		int []v= {0,20, 6, 8, 15, 18};
		int []w= {0,5, 4, 8, 6, 9};
		int c=18;
		int [][]m = new int[w.length][c+1];
		int []x = new int [w.length];
		knapsack(v,w,c,m);
		traceback(m,w,c,x);
		System.out.println("����ֵΪ:"+m[1][c]);
		System.out.print("1��ʾ������������Ʒ����ʾû���룺");
		for(int i=1;i<x.length;i++) {
			System.out.print(" "+x[i]);
		}
	}
	public static void knapsack(int []v,int []w, int c,int [][]m) {
		int n=v.length-1;
		int jMax = Math.min(w[n]-1,c);	//��ʼ������ m
		for(int j=0;j<=jMax;j++) {
			m[n][j]=0;
			
		}
		for(int j=w[n];j<=c;j++) {	    //ֻ����Ʒ n ʱ�������Ʒ n ������С�ڱ������������򱳰��з�����Ʒ n
			m[n][j]=v[n];
			
		}
		for(int i = n-1;i >= 0; i--) {		//�����������������½���򱳰��������Ʒ n-1, n-3 ... 1
			jMax=Math.min(w[i]-1,c);		
			for(int j=0;j<=jMax;j++) {		//����������Ϊ j ʱ����¼��Ʒ i+1��n��
				m[i][j]=m[i+1][j];
			}
			//���������Ʒ i ������ֵΪ m[i + 1][j - w[i]] + v[i] , �����������Ʒ i �����ֵΪ m[i + 1][j]
			for(int j=w[i];j<=c;j++) {		
				m[i][j]=Math.max(m[i+1][j],m[i+1][j-w[i]]+v[i]);
			}
		}
		m[0][c]=m[1][c];
		if(c>=w[1]) {
			m[1][c]=Math.max(m[1][c],m[2][c-w[1]]+v[1]);
		}
	}
	//�������Ž⣬�ж��Ƿ��򱳰�������Ʒ i
	public static void traceback(int [][]m, int []w, int c, int []x) {
		int n=w.length-1;
		for(int i=1;i<n;i++) {
			if(m[i][c]==m[i+1][c]) {	//���û���򱳰��з�����Ʒ���� x[i] ��Ϊ 0
				x[i]=0;
			}
			else {			//�򱳰��з�������Ʒ��x[i] ��Ϊ 1
				x[i]=1;
				c-=w[i];		//������ʣ������
			}
		}
		x[n]=(m[n][c]>0)?1:0;		//�ж����һ����Ʒ��û�з��뱳����
	}
}
