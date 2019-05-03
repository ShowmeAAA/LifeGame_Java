package showlifegame;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import enumeration.CellStatus;

public class Cell implements PropertyChangeListener {
	protected PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	protected CellStatus cellStatusMatrix[][];		//细胞状态矩阵 ALIVE还是DEAD
	private CellStatus curCellStatusMatrix[][];		//当前代的细胞状态矩阵
	private int cellNumOfEachRow = 100;
	private int cellNumOfEachColumn = 100;
	
	/**
	 * 为细胞状态矩阵分配空间并注册观察者cellPanel
	 * @param cellPanel	 观察者
	 */
	public Cell(CellPanel cellPanel) {
		cellStatusMatrix = new CellStatus[cellNumOfEachRow][cellNumOfEachColumn];
		//注册观察者
		pcs.addPropertyChangeListener(cellPanel);
	}
	
	public CellStatus[][] getCellStatusMatrix() {
		return cellStatusMatrix;
	}
	
	/**
	 * 更新细胞状态矩阵并广播事件给cellPanel
	 */
	private void evoluteCellStatusMatrix() {
		int x = 0;
		int y = 0;
		 
		 //复制当代细胞状态二维数组
		 curCellStatusMatrix = new CellStatus[cellNumOfEachRow][cellNumOfEachColumn];
		 for (x = 0 ; x < cellNumOfEachRow; x ++) {
			 for (y = 0; y < cellNumOfEachColumn; y ++) {
				 curCellStatusMatrix[x][y] = cellStatusMatrix[x][y];
			 }
		 }
		 
		 //根据当前代curCellStatusMatrix来更新cellStatusMatrix
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
			//搜索8个格子 得出活邻居个数
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
			
			//根据当代情况和活邻居个数得出下代情况
			if (curCellStatusMatrix[x][y] == CellStatus.ALIVE) {//当代为活细胞
				if (aliveNeighbourCnt == 2 || aliveNeighbourCnt == 3)
					return true;
				else 
					return false;
			}
			else{//当代为死细胞
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
	 * 从数据库中获取初始细胞状态矩阵并广播事件给cellPanel
	 * 一般由用户在复选框中选择，然后复选框将这个事件告诉cell，cell调用这个方法
	 * @param 游戏名字 如滑翔机等
	 */
	public void initCellStatusMatrix(String name) {
		//根据名字选出初始细胞状态矩阵
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
	 * 清除细胞状态矩阵并广播事件给cellPanel
	 */
	public void clearCellStatusMatrix() {
		for (int x = 0; x < cellNumOfEachRow; x ++)
			for (int y = 0; y < cellNumOfEachColumn; y ++)
				cellStatusMatrix[x][y] = CellStatus.DEAD;
		
		pcs.firePropertyChange("cellStatusMatrix", null, getCellStatusMatrix());
	}

	
	/**
	 * 接受到事件：闹钟响。
	 * 作出反应：更新细胞状态矩阵
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		evoluteCellStatusMatrix();
	}

}
