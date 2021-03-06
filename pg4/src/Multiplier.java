import java.util.*;
import java.lang.*;
import java.util.concurrent.Semaphore;

class Multiplier implements Runnable{
    /**
	 * internal variable used to represent the range values
	 */
    private int start, end, threadId;

	/**
	 * function of Adder
	 *
	 * @param threadId
	 * @param threadCount
	 */
    public Multiplier(int threadId, int threadCount){
        this.threadId = threadId;

        int per = Global.size/threadCount;
        this.start = threadId * per;
        this.end = this.start+per-1;

        if(Global.size - this.end < per){
            this.end = Global.size-1;
        }

    }

	/**
	 * function of run
	 *
	 */
    public void run(){
        try{
            Formatter fmt = new Formatter(new StringBuilder());
            //System.err.println(fmt.format("Mutiplier:threadId=%s, range=%s-%s", this.threadId, this.start, this.end));


            for(int i=this.start; i<=this.end;i++){
                Global.c[i] = Global.a[i] * Global.b[i];
            }
            // release lock
            Global.EnteringPhase2.release();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
