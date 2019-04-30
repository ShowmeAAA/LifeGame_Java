package businessSystem;

import uiSystem.BoardPanel;

public class MyTimer extends Thread{
	private int timeQuantum;		//��ʱ���
	private BoardPanel boardPanel;	//�۲���
    
    public MyTimer(int timeQuantum, BoardPanel boardPanel) {
    	this.timeQuantum = timeQuantum;
    	this.boardPanel = boardPanel;
    }
    	
    public void run() {
    	while (true) {  
    		//ÿ�������������£��༴��ʱ�������¼����۲��ߴ����¼�
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
    
    
    //��ʼ��ʱ
    public void startTimer() {
    	this.start();
    }
    
    //ֹͣ��ʱ
    public void stopTimer() {
    	try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
