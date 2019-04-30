package uiSystem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame{
	private BoardPanel boardPanel;
	private JButton startButton;
	private JButton stopButton;
	
	public MainFrame(String title) {
		super(title);
		
		//��ʼ��ť
		startButton = new JButton("start");
		startButton.setBounds(1100, 100, 70, 50);
		this.getContentPane().add(startButton);
		
		//ֹͣ��ť
		stopButton = new JButton("stop");
		stopButton.setBounds(1100, 200, 70, 50);
		this.getContentPane().add(stopButton);
		
		//�������
		//100�����ӣ�ÿ�����ӱ߳�Ϊ10
		boardPanel = new BoardPanel(100, 10);
		boardPanel.initCellAndRepaint();
		this.getContentPane().add(boardPanel);
		
		//����frame����
		this.setSize(1200, 1000);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}
	public void setBoardPanel(BoardPanel boardPanel) {
		this.boardPanel = boardPanel;
	}
}
