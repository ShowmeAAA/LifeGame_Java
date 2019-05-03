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
		
		//初始化将定时器设为不工作状态
		lifeGame.myTimer.stopTimer();
		
		//使定时器的线程开始 这个线程一直保持工作到应用程序结束
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

	//主面板
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
			//控制器“START”按钮控制模型myTimer对象和cell对象
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
			//控制器“STOP”按钮控制模型myTimer对象
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
			//控制器“CLEAR”按钮控制模型myTimer对象和cell对象
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
