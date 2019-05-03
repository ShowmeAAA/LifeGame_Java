package showlifegame;

import java.beans.*;

import enumeration.TimerStatus;

public class MyTimer extends Thread{
	//添加一个被观察对象 这是代替继承Observable的方式
	//将侦听任务委派给它
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	protected int timeQuantum;				//时间间隔 以微秒为单位
	protected TimerStatus timerStatus;		//定时器的状态 WORKING还是NOT_WORKING
	
	public TimerStatus getTimerStatus() {
		return timerStatus;
	}


	public void setTimerStatus(TimerStatus timerStatus) {
		this.timerStatus = timerStatus;
	}

	
	/**
	 * constructor
	 * @param timeQuantum	时间间隔 微秒为单位
	 * @param cell			观察者
	 */
	public MyTimer(int timeQuantum, Cell cell) {
		this.timeQuantum = timeQuantum;
		//注册观察者
		pcs.addPropertyChangeListener(cell);
	}
	
	/**
	 * 开始计时
	 * 不需要广播事件cell
	 */
	public void startTimer() {
		setTimerStatus(TimerStatus.WORKING);
	}
	
	/**
	 * 停止计时
	 * 不需要广播事件给cell
	 */
	public void stopTimer() {
		setTimerStatus(TimerStatus.NOT_WORKING);
	}
	
	@Override
	public void run() {
    	while (true) {  
    		//闹钟响就广播事件给cell，不需要传送事件信息，因为只有闹钟响才会通知cell   
    		if (timerStatus == TimerStatus.WORKING)
    			pcs.firePropertyChange(null, null, null);
    		//else 闹钟不处于工作状态则一直循环
    		
	         try {  
	               Thread.sleep(timeQuantum);  
	         } catch (InterruptedException e) {  
	               e.printStackTrace();  
	         }  
	            
        }  
    }
	
}
