package showlifegame;

import java.beans.*;

import enumeration.TimerStatus;

public class MyTimer extends Thread{
	//���һ�����۲���� ���Ǵ���̳�Observable�ķ�ʽ
	//����������ί�ɸ���
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	protected int timeQuantum;				//ʱ���� ��΢��Ϊ��λ
	protected TimerStatus timerStatus;		//��ʱ����״̬ WORKING����NOT_WORKING
	
	public TimerStatus getTimerStatus() {
		return timerStatus;
	}


	public void setTimerStatus(TimerStatus timerStatus) {
		this.timerStatus = timerStatus;
	}

	
	/**
	 * constructor
	 * @param timeQuantum	ʱ���� ΢��Ϊ��λ
	 * @param cell			�۲���
	 */
	public MyTimer(int timeQuantum, Cell cell) {
		this.timeQuantum = timeQuantum;
		//ע��۲���
		pcs.addPropertyChangeListener(cell);
	}
	
	/**
	 * ��ʼ��ʱ
	 * ����Ҫ�㲥�¼�cell
	 */
	public void startTimer() {
		setTimerStatus(TimerStatus.WORKING);
	}
	
	/**
	 * ֹͣ��ʱ
	 * ����Ҫ�㲥�¼���cell
	 */
	public void stopTimer() {
		setTimerStatus(TimerStatus.NOT_WORKING);
	}
	
	@Override
	public void run() {
    	while (true) {  
    		//������͹㲥�¼���cell������Ҫ�����¼���Ϣ����Ϊֻ��������Ż�֪ͨcell   
    		if (timerStatus == TimerStatus.WORKING)
    			pcs.firePropertyChange(null, null, null);
    		//else ���Ӳ����ڹ���״̬��һֱѭ��
    		
	         try {  
	               Thread.sleep(timeQuantum);  
	         } catch (InterruptedException e) {  
	               e.printStackTrace();  
	         }  
	            
        }  
    }
	
}
