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
		
		//开始按钮
		startButton = new JButton("start");
		startButton.setBounds(1100, 100, 70, 50);
		this.getContentPane().add(startButton);
		
		//停止按钮
		stopButton = new JButton("stop");
		stopButton.setBounds(1100, 200, 70, 50);
		this.getContentPane().add(stopButton);
		
		//棋盘面板
		//100个格子，每个格子边长为10
		boardPanel = new BoardPanel(100, 10);
		boardPanel.initCellAndRepaint();
		this.getContentPane().add(boardPanel);
		
		//设置frame自身
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
