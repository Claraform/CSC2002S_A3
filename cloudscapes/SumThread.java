package cloudscapes;

//import ForkJoinSum;
import java.util.concurrent.RecursiveTask;

class SumThread extends RecursiveTask<vector>{
	int lo;
	int hi;
	vector[][][] arr;
	CloudDataPar cdp;

	SumThread(vector[][][] v, int l, int h, CloudDataPar cd){
		arr=v;
		lo=l;
		hi=h;
		cdp=cd;
	}
	protected vector compute (){
		if (hi-lo < 500){
			vector ans=new vector();
			for(int i=lo; i < hi; i++){
				int [] ind = new int[3];
				cdp.locate(i, ind);
				int t=ind[0];
				int x=ind[1];
				int y=ind[2];
				ans.x+= arr[t][x][y].x;
				ans.y+= arr[t][x][y].y;
			}	
			return ans;
		} else {
			SumThread left = new SumThread(arr,lo,(hi+lo)/2,cdp);
			SumThread right= new SumThread(arr,(hi+lo)/2,hi,cdp);
			left.fork();
			vector rightAns = right.compute();
			vector leftAns = left.join();
			vector result = new vector();
			result.x = rightAns.x+leftAns.x;
			result.y=rightAns.y+leftAns.y;
			return result;
		} } }
//static final ForkJoinPool fjPool = new ForkJoinPool();
//vector sum(vector[] arr, int elements){
//	return ForkJoinPool.commonPool().invoke
//		(new SumThread(arr,0,elements));
//

