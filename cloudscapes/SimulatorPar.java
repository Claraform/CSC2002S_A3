package cloudscapes;


import java.util.concurrent.ForkJoinPool;
import java.util.Scanner;

public class SimulatorPar{
	int numTs;
	static final ForkJoinPool fjPool = new ForkJoinPool();
	static vector sum(vector[][][] arr, int elements, CloudDataPar cd){
		return ForkJoinPool.commonPool().invoke
			(new SumThread(arr,0,elements, cd));
	}
	static final ForkJoinPool pool = new ForkJoinPool();
	static int goClass(int elements, CloudDataPar cd){
		return ForkJoinPool.commonPool().invoke
			(new ClassThread(0,elements, cd));
	}

	public static void main (String[] args){
		float xSum=0;
		float ySum=0;
		CloudDataPar cd = new CloudDataPar();
		String filename=args[0];
		System.out.println(filename);
		String output=args[1];
		cd.readData(filename);
		int length = cd.dim();
		vector wind=sum(cd.advection,length, cd);
		goClass(length,cd);
		//for (int i=0; i<length; i++){
		//	int [] ind = new int[3];
		//    	cd.locate(i, ind);
		//	int t=ind[0];
		//	int x=ind[1];
		//	int y=ind[2];
		//	xSum=xSum+cd.advection[t][x][y].x;	
		//	ySum=ySum+cd.advection[t][x][y].y;
		//	cd.classify(t,x,y);
		//}
		//vector wind = new vector();
		wind.x = wind.x/length;
		wind.y = wind.y/length;
		//
		cd.writeData(output, wind);

	}

}
