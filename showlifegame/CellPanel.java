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
	
	//���캯����һ��Ҫ��ȫ��ϸ����Ϊ��ϸ��
	//���� ���߳���һ��ʼ����cellPanelʱ��cellStatusMatrix[][]Ϊnull�������쳣
	public CellPanel() {
		cellStatusMatrix = new CellStatus[cellNumOfEachRow][cellNumOfEachColumn];
		for (int x = 0; x < cellNumOfEachRow; x ++)
			for (int y = 0; y < cellNumOfEachColumn; y ++)
				cellStatusMatrix[x][y] = CellStatus.DEAD;
	}
	
	/**
	 * ��������cell��֪ͨ
	 * ������Ӧ���ػ�cellPanle
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		cellStatusMatrix = (CellStatus[][]) evt.getNewValue();
		repaint();
	}
	
	//repaint()�����paint()
	//paint()�е��� paintComponent(), paintBorder(),
	//paintChildren()���ֱ��������Ʊ������߿��ӿؼ�
	
	//��дpaintComponent()
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
