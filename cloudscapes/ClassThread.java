package cloudscapes;

//import ForkJoinSum;
import java.util.concurrent.RecursiveTask;

class ClassThread extends RecursiveTask<Integer>{
	int lo;
	int hi;
	CloudDataPar cdp;

	ClassThread(int l, int h, CloudDataPar cd){
		lo=l;
		hi=h;
		cdp=cd;
	}
	protected Integer compute (){
		if (hi-lo < 500){
			for(int i=lo; i < hi; i++){
				int [] ind = new int[3];
				cdp.locate(i, ind);
				int t=ind[0];
				int x=ind[1];
				int y=ind[2];
				cdp.classify(t,x,y);
			}	
			return 1;
		} else {
			ClassThread left = new ClassThread(lo,(hi+lo)/2,cdp);
			ClassThread right= new ClassThread((hi+lo)/2,hi,cdp);
			left.fork();
			right.compute();
			left.join();
			return 1;
			
		} } }
//static final ForkJoinPool fjPool = new ForkJoinPool();
//vector sum(vector[] arr, int elements){
//	return ForkJoinPool.commonPool().invoke
//		(new SumThread(arr,0,elements));


