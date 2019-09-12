package cloudscapes;

import java.util.Scanner;

public class simulator{

	public static void main (String[] args){
		float xSum=0;
		float ySum=0;
		CloudData cd = new CloudData();
		String filename=args[0];
		System.out.println(filename);
		String output=args[1];
		cd.readData(filename);
		int length = cd.dim();
		
		for (int i=0; i<length; i++){
			int [] ind = new int[3];
		      	cd.locate(i, ind);
			xSum=xSum+cd.advection[ind[0]][ind[1]][ind[2]].x;	
			ySum=ySum+cd.advection[ind[0]][ind[1]][ind[2]].y;
			//vector wind = cd.advection[ind[0]][ind[1]][ind[2]];
			//cd.writeData(output, wind);
		}
		vector wind = new vector();
		wind.x = xSum/length;
		wind.y = ySum/length;
		//
		cd.writeData(output, wind);
	
	}
	int classify(vector v, int [] ind){
		int y=ind[2];
		int x=ind[1];
		int t=ind[0];
		for(4){
				
		}
	}


}
