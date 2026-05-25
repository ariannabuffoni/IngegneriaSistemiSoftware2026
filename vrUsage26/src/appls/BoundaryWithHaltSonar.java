/**
 * BoundaryWithStep
 * Percorre il perimetro usando step di RobotObj26
 * 
 * Notare la copia di logback.xml per la esecuzione 
 * con RunAs in eclipse
 * 
 * Notare i threads
 */

package appls;
 
import robots.RobotObj26;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.interfaces.IObserverMsg;
import unibo.basicomm23.utils.CommUtils;
import unibo.basicomm23.utils.LogUtils;
  
public class BoundaryWithHaltSonar implements IObserverMsg { 
    private RobotObj26 robot;
	private int n = 0;
    private String logFName = "vrusage26.log"; //see logback.xml
     private int stepTime = 345;   //sonar at 0.19
    private LogUtils log  = new LogUtils("bstep");
 
    public BoundaryWithHaltSonar(String addr) {
        CommUtils.outblue("TestMovesUsingWs |  CREATING ..." + addr);  
        robot = RobotObj26.create(addr, this,logFName);
        //robot.setTrace(true);
        log.clearlog("logs/"+logFName);
        CommUtils.aboutThreads("main");
    }
    
    //Basato su step sincorno. Molto più semplice ....
    public void doJob() throws Exception {
    	askUser();
    	robot.halt();
     	while( n < 4 ) {
    		walk();
    		CommUtils.outblue("turning");
			log.info("turned when n="+n);
			robot.turnLeft();
    		n++;
     	}
     }
     
    protected void walk() throws Exception{
       	boolean r = robot.step(stepTime);
       	while( r ) {
       		r = robot.step(stepTime);
       	}
    }
    
    protected void askUser() {
    	CommUtils.waitTheUser("PUT ROBOT in HOME and hit");
    }   
 /*
MAIN
 */
    public static void main(String[] args) {
        try{
    		CommUtils.aboutThreads("Before start - ");
            BoundaryWithHaltSonar appl = new BoundaryWithHaltSonar( "localhost" );
            appl.doJob();
         	CommUtils.aboutThreads("At end - ");
        } catch( Exception ex ) {
            CommUtils.outred("BoundaryUsingVrBasicAdapter | main ERROR: " + ex.getMessage());
        }
    }

	@Override
	public void update(IApplMessage msg) {
		if (msg.msgId().equals("sonardata")) {
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			CommUtils.aboutThreads("update");
			CommUtils.delay(2000);
		}
		
	}

 
 


}

