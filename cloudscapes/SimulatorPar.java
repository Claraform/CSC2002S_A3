package cloudscapes;

import java.util.Scanner;

public class SimulatorPar{

	public static void main (String[] args){
		float xSum=0;
		float ySum=0;
		CloudDataPar cd = new CloudDataPar();
		String filename=args[0];
		System.out.println(filename);
		String output=args[1];
		cd.readData(filename);
		int length = cd.dim();
		
		for (int i=0; i<length; i++){
			int [] ind = new int[3];
		      	cd.locate(i, ind);
			int t=ind[0];
			int x=ind[1];
			int y=ind[2];
			xSum=xSum+cd.advection[t][x][y].x;	
			ySum=ySum+cd.advection[t][x][y].y;
			cd.classify(t,x,y);
		}
		vector wind = new vector();
		wind.x = xSum/length;
		wind.y = ySum/length;
		//
		cd.writeData(output, wind);
	
	}

}
