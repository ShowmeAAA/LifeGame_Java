package uiSystem;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import businessSystem.Cell;

public class BoardPanel extends JPanel{
	private int  boardSize;
	private int cellSize;
	private Cell cell;
	
	public BoardPanel(int boardSize, int cellSize) {
		super();
		this.boardSize = boardSize;
		this.cellSize = cellSize;
		//设置面板大小
		//this.setBounds(0, 0, 1000, 1000);
		cell = new Cell(boardSize);
	}
	
	public void evoluteCellAndRepaint() {
		cell.evolute();
		repaint();
	}
	
	public void initCellAndRepaint() {
		cell.init();
		repaint();
	}
	
	public void clearCellAndRepaint() {
		cell.clear();
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		boolean cellStatusTemp[][] = cell.getCellStatus();
		for (int x = 0; x < boardSize; x++)
            for (int y = 0; y < boardSize; y++) {
               //活细胞true 黑色
               //死细胞false 背景色
               if (cellStatusTemp[x][y])
            	   g.setColor(Color.green);
               else
            	   g.setColor(Color.black);
               g.fillRect(x * cellSize, y * cellSize, cellSize - 1, cellSize - 1);
            }
	}
}
