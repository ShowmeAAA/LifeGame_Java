package businessSystem;

import uiSystem.BoardPanel;

public class MyTimer extends Thread{
	private int timeQuantum;		//定时间隔
	private BoardPanel boardPanel;	//观察者
    
    public MyTimer(int timeQuantum, BoardPanel boardPanel) {
    	this.timeQuantum = timeQuantum;
    	this.boardPanel = boardPanel;
    }
    	
    public void run() {
    	while (true) {  
    		//每次闹钟响做的事，亦即定时器发出事件，观察者处理事件
    		boardPanelPerform();
            try {  
                Thread.sleep(timeQuantum);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }
    
    private void boardPanelPerform() {
    	boardPanel.evoluteCellAndRepaint();
    }
    
    
    //开始计时
    public void startTimer() {
    	this.start();
    }
    
    //停止计时
    public void stopTimer() {
    	try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
