package cloudscapes;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Locale;
import java.lang.Math;

public class CloudDataPar {

	vector [][][] advection; // in-plane regular grid of wind vectors, that evolve over time
	float [][][] convection; // vertical air movement strength, that evolves over time
	int [][][] classification; // cloud type per grid point, evolving over time
	int dimx, dimy, dimt; // data dimensions

	// overall number of elements in the timeline grids
	int dim(){
		return dimt*dimx*dimy;
	}
	
	// convert linear position into 3D location in simulation grid
	void locate(int pos, int [] ind)
	{
		ind[0] = (int) pos / (dimx*dimy); // t
		ind[1] = (pos % (dimx*dimy)) / dimy; // x
		ind[2] = pos % (dimy); // y
	}
	
	// read cloud simulation data from file
	void readData(String fileName){ 
		try{ 
			Scanner sc = new Scanner(new File(fileName), "UTF-8").useLocale(Locale.US);
			
			// input grid dimensions and simulation duration in timesteps
			dimt = sc.nextInt();
			dimx = sc.nextInt(); 
			dimy = sc.nextInt();
			
			// initialize and load advection (wind direction and strength) and convection
			advection = new vector[dimt][dimx][dimy];
			convection = new float[dimt][dimx][dimy];
			for(int t = 0; t < dimt; t++)
				for(int x = 0; x < dimx; x++)
					for(int y = 0; y < dimy; y++){
						advection[t][x][y] = new vector();
						advection[t][x][y].x = sc.nextFloat();
						advection[t][x][y].y = sc.nextFloat();
						convection[t][x][y] = sc.nextFloat();
					}
			
			classification = new int[dimt][dimx][dimy];
			sc.close(); 
		} 
		catch (IOException e){ 
			System.out.println("Unable to open input file "+fileName);
			e.printStackTrace();
		}
		catch (java.util.InputMismatchException e){ 
			System.out.println("Malformed input file "+fileName);
			e.printStackTrace();
		}
	}
	
	// write classification output to file
	void writeData(String fileName, vector wind){
		 try{ 
			 FileWriter fileWriter = new FileWriter(fileName);
			 PrintWriter printWriter = new PrintWriter(fileWriter);
			 printWriter.printf("%d %d %d\n", dimt, dimx, dimy);
			 printWriter.printf("%f %f\n", wind.x, wind.y);
			 
			 for(int t = 0; t < dimt; t++){
				 for(int x = 0; x < dimx; x++){
					for(int y = 0; y < dimy; y++){
						printWriter.printf("%d ", classification[t][x][y]);
					}
				 }
				 printWriter.printf("\n");
		     }
				 
			 printWriter.close();
		 }
		 catch (IOException e){
			 System.out.println("Unable to open output file "+fileName);
				e.printStackTrace();
		 }
	}
	void classify(int t, int x, int y){
		int[] limits = getLimits(x,y);
		float xSum=0;
		float ySum=0;
		int elements=0;
		float uplift = Math.abs(convection[t][x][y]);
		for (int i=limits[0]; i<=limits[1]; i++){
			for (int j=limits[2]; j<=limits[3]; j++){
                        	elements=elements+1;
				ySum=ySum+advection[t][i][j].y;
				xSum=xSum+advection[t][i][j].x;
			}
                }
		float xMean = xSum/elements;
		float yMean = ySum/elements;
		float magn =(float)Math.sqrt(Math.pow(xMean,2)+Math.pow(yMean,2));
		int code=3; //error code
		if (uplift>magn){
			code=0;
		}
		else if (magn>0.2 && magn>=uplift){
			code=1;
		}
		else{
			code=2;
		}
		classification[t][x][y]=code;

	}	
	int[] getLimits(int x, int y){
		int[] limits = new int[4];
		if (x==0){
			limits[0]=0; //start co-ord
		} 
		else {
			limits[0]=x-1;//start
		}
		if (y==0){
			limits[2]=0;//start
		} 
                else {
			limits[2]=y-1;//start
		}
		if (x==dimx-1){
			limits[1]=x;
		}
		else {limits[1]=x+1;}
		if (y==dimy-1){
			limits[3]=y;
		}
		else {limits[3]=y+1;}
		return limits;
	}

}
