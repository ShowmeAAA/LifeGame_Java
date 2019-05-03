package showlifegame;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import enumeration.CellStatus;

public class CellPanel extends JPanel implements PropertyChangeListener {
	private CellStatus cellStatusMatrix[][];
	private int cellSize = 10;
	private int cellNumOfEachRow = 100;
	private int cellNumOfEachColumn = 100;
	
	//构造函数中一定要将全部细胞设为死细胞
	//否则 主线程在一开始绘制cellPanel时，cellStatusMatrix[][]为null会引发异常
	public CellPanel() {
		cellStatusMatrix = new CellStatus[cellNumOfEachRow][cellNumOfEachColumn];
		for (int x = 0; x < cellNumOfEachRow; x ++)
			for (int y = 0; y < cellNumOfEachColumn; y ++)
				cellStatusMatrix[x][y] = CellStatus.DEAD;
	}
	
	/**
	 * 接受来自cell的通知
	 * 作出反应：重绘cellPanle
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		cellStatusMatrix = (CellStatus[][]) evt.getNewValue();
		repaint();
	}
	
	//repaint()会调用paint()
	//paint()中调用 paintComponent(), paintBorder(),
	//paintChildren()，分别用来绘制背景，边框，子控件
	
	//重写paintComponent()
	public void paintComponent(Graphics g) {
		// paint the background
		super.paintComponent(g); 
		for (int x = 0; x < cellNumOfEachRow; x++) {
            for (int y = 0; y < cellNumOfEachColumn; y++) {
               if (cellStatusMatrix[x][y] == CellStatus.ALIVE)
            	   g.setColor(Color.green);
               else
            	   g.setColor(Color.black);
               g.fillRect(x * cellSize, y * cellSize, cellSize - 1, cellSize - 1);
            }
		}
	}
	
}
