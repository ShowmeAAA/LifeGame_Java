package showlifegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class LifeGame {
	public static void main(String[] args) {
		LifeGame lifeGame = new LifeGame();
		lifeGame.mainFrame().setVisible(true);
		
		//��ʼ������ʱ����Ϊ������״̬
		lifeGame.myTimer.stopTimer();
		
		//ʹ��ʱ�����߳̿�ʼ ����߳�һֱ���ֹ�����Ӧ�ó������
		lifeGame.myTimer.start();
	}
	
	
	//view
	protected CellPanel cellPanel;
	
	//controller
	protected JButton startButton;
	protected JButton stopButton;
	protected JButton clearButton;
	
	//model
	protected Cell cell = new Cell(cellPanel());
	protected MyTimer myTimer = new MyTimer(500, cell);
	
	
	
	private CellPanel cellPanel() {
		if (cellPanel == null) {
			cellPanel = new CellPanel();
			cellPanel.setBounds(0, 0, 1000, 1000);
		}
		return cellPanel;
	}

	//�����
	protected JFrame mainFrame() {
		JFrame f = new JFrame("Enjoy Life Game");
		f.setLayout(null);
		f.setSize(1200, 1000);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.getContentPane().add(cellPanel());
		f.getContentPane().add(startButton());
		f.getContentPane().add(stopButton());
		f.getContentPane().add(clearButton());
		
		return f;
	}
	
	protected JButton startButton() {
		if (startButton == null) {
			startButton = new JButton("START");
			startButton.setBounds(1050, 20, 100, 50);
			//addChangeListener
			//��������START����ť����ģ��myTimer�����cell����
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cell.initCellStatusMatrix("small exploder");
					myTimer.startTimer();
				}
				
			});
			
		}
		return startButton;
	}
	
	protected JButton stopButton() {
		if (stopButton == null) {
			stopButton = new JButton("STOP");
			stopButton.setBounds(1050, 120, 100, 50);
			//addChangeListener
			//��������STOP����ť����ģ��myTimer����
			stopButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					myTimer.stopTimer();
				}
			});
			
		}
		return stopButton;
	}
	
	protected JButton clearButton() {
		if (clearButton == null) {
			clearButton = new JButton("CLEAR");
			clearButton.setBounds(1050, 220, 100, 50);
			//addChangeListener
			//��������CLEAR����ť����ģ��myTimer�����cell����
			clearButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					myTimer.stopTimer();
					cell.clearCellStatusMatrix();
				}
			});
			
		}
		return clearButton;
	}
	
}
