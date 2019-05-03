package showlifegame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import enumeration.CellStatus;

public class Cell implements PropertyChangeListener {
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	protected CellStatus cellStatusMatrix[][];		//ϸ��״̬���� ALIVE����DEAD
	private CellStatus curCellStatusMatrix[][];		//��ǰ����ϸ��״̬����
	private int cellNumOfEachRow = 100;
	private int cellNumOfEachColumn = 100;
	
	/**
	 * Ϊϸ��״̬�������ռ䲢ע��۲���cellPanel
	 * @param cellPanel	 �۲���
	 */
	public Cell(CellPanel cellPanel) {
		cellStatusMatrix = new CellStatus[cellNumOfEachRow][cellNumOfEachColumn];
		//ע��۲���
		pcs.addPropertyChangeListener(cellPanel);
	}
	
	public CellStatus[][] getCellStatusMatrix() {
		return cellStatusMatrix;
	}
	
	/**
	 * ����ϸ��״̬���󲢹㲥�¼���cellPanel
	 */
	private void evoluteCellStatusMatrix() {
		int x = 0;
		int y = 0;
		 
		 //���Ƶ���ϸ��״̬��ά����
		 curCellStatusMatrix = new CellStatus[cellNumOfEachRow][cellNumOfEachColumn];
		 for (x = 0 ; x < cellNumOfEachRow; x ++) {
			 for (y = 0; y < cellNumOfEachColumn; y ++) {
				 curCellStatusMatrix[x][y] = cellStatusMatrix[x][y];
			 }
		 }
		 
		 //���ݵ�ǰ��curCellStatusMatrix������cellStatusMatrix
		 for (x = 0 ; x < cellNumOfEachRow; x ++) {
			 for (y = 0; y < cellNumOfEachColumn; y ++) {
				 if (isAliveInNextGeneration(x, y))
					 cellStatusMatrix[x][y] = CellStatus.ALIVE;
				 else
					 cellStatusMatrix[x][y] = CellStatus.DEAD;
			 }
		 }
		
		pcs.firePropertyChange("cellStatusMatrix", null, getCellStatusMatrix());
	}
	
		
	private boolean isAliveInNextGeneration(int x, int y) {
			//����8������ �ó����ھӸ���
			int aliveNeighbourCnt = 0;
			if (isPositionLegal(x - 1, y) && curCellStatusMatrix[x - 1][y] == CellStatus.ALIVE)
				aliveNeighbourCnt ++;
			if (isPositionLegal(x - 1, y + 1) && curCellStatusMatrix[x - 1][y + 1] == CellStatus.ALIVE)
				aliveNeighbourCnt ++;
			if (isPositionLegal(x - 1, y - 1) && curCellStatusMatrix[x - 1][y - 1] == CellStatus.ALIVE)
				aliveNeighbourCnt ++;
			if (isPositionLegal(x, y + 1) && curCellStatusMatrix[x][y + 1] == CellStatus.ALIVE)
				aliveNeighbourCnt ++;
			if (isPositionLegal(x, y - 1) && curCellStatusMatrix[x][y - 1] == CellStatus.ALIVE)
				aliveNeighbourCnt ++;
			if (isPositionLegal(x + 1, y + 1) && curCellStatusMatrix[x + 1][y + 1] == CellStatus.ALIVE)
				aliveNeighbourCnt ++;
			if (isPositionLegal(x + 1, y) && curCellStatusMatrix[x + 1][y] == CellStatus.ALIVE)
				aliveNeighbourCnt ++;
			if (isPositionLegal(x + 1, y - 1) && curCellStatusMatrix[x + 1][y - 1] == CellStatus.ALIVE)
				aliveNeighbourCnt ++;
			
			//���ݵ�������ͻ��ھӸ����ó��´����
			if (curCellStatusMatrix[x][y] == CellStatus.ALIVE) {//����Ϊ��ϸ��
				if (aliveNeighbourCnt == 2 || aliveNeighbourCnt == 3)
					return true;
				else 
					return false;
			}
			else{//����Ϊ��ϸ��
				if (aliveNeighbourCnt == 3)
					return true;
				else
					return false;
			}
		}
		
	private boolean isPositionLegal(int x, int y) {
			if (x > 0 && x < cellNumOfEachRow&& y > 0 && y < cellNumOfEachColumn)
				return true;
			else
				return false;
		}
	
	
	
	/**
	 * �����ݿ��л�ȡ��ʼϸ��״̬���󲢹㲥�¼���cellPanel
	 * һ�����û��ڸ�ѡ����ѡ��Ȼ��ѡ������¼�����cell��cell�����������
	 * @param ��Ϸ���� �绬�����
	 */
	public void initCellStatusMatrix(String name) {
		//��������ѡ����ʼϸ��״̬����
		for (int x = 0; x < cellNumOfEachRow; x ++)
			for (int y = 0; y < cellNumOfEachColumn; y ++)
				cellStatusMatrix[x][y] = CellStatus.DEAD;
		
		//Small Exploder
		cellStatusMatrix[30][30] = CellStatus.ALIVE;
		cellStatusMatrix[31][30] = CellStatus.ALIVE;
		cellStatusMatrix[31][31] = CellStatus.ALIVE;
		cellStatusMatrix[31][29] = CellStatus.ALIVE;
		cellStatusMatrix[32][29] = CellStatus.ALIVE;
		cellStatusMatrix[32][31] = CellStatus.ALIVE;
		cellStatusMatrix[33][30] = CellStatus.ALIVE;
		
		pcs.firePropertyChange("cellStatusMatrix", null, getCellStatusMatrix());
	}
	
	/**
	 * ���ϸ��״̬���󲢹㲥�¼���cellPanel
	 */
	public void clearCellStatusMatrix() {
		for (int x = 0; x < cellNumOfEachRow; x ++)
			for (int y = 0; y < cellNumOfEachColumn; y ++)
				cellStatusMatrix[x][y] = CellStatus.DEAD;
		
		pcs.firePropertyChange("cellStatusMatrix", null, getCellStatusMatrix());
	}

	
	/**
	 * ���ܵ��¼��������졣
	 * ������Ӧ������ϸ��״̬����
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		evoluteCellStatusMatrix();
	}

}
