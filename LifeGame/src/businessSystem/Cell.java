package businessSystem;

public class Cell {
	private boolean cellStatus[][];
	private boolean curGenerationStatus[][];
	private int boardSize;
	
	public boolean[][] getCellStatus() {
		return cellStatus;
	}

	public void init() {
		//从数据库中取一组初始化数据
		for (int x = 0; x < boardSize; x ++) {
			for (int y = 0; y < boardSize; y ++) {
				cellStatus[x][y] = false;
			}
		}
		
		/*
		cellStatus[50][50] = true;
		cellStatus[51][51] = true;
		cellStatus[51][52] = true;
		cellStatus[50][52] = true;
		cellStatus[49][52] = true;*/
		/*
		cellStatus[40][40] = true;
		cellStatus[41][40] = true;
		cellStatus[42][40] = true;
		cellStatus[43][40] = true;
		cellStatus[44][40] = true;
		cellStatus[45][40] = true;
		cellStatus[46][40] = true;
		cellStatus[47][40] = true;
		cellStatus[48][40] = true;
		cellStatus[49][40] = true;
		cellStatus[50][40] = true;*/
		
		//Small Exploder
		cellStatus[30][30] = true;
		cellStatus[31][30] = true;
		cellStatus[31][31] = true;
		cellStatus[31][29] = true;
		cellStatus[32][29] = true;
		cellStatus[32][31] = true;
		cellStatus[33][30] = true;
	}
	
	//演化 更新cellStatus[][]
	public void evolute() {
		 int x = 0;
		 int y = 0;
		 
		 //复制当代细胞状态二维数组
		 curGenerationStatus = new boolean[boardSize][boardSize];
		 for (x = 0 ; x < boardSize; x ++) {
			 for (y = 0; y < boardSize; y ++) {
				 curGenerationStatus[x][y] = cellStatus[x][y];
			 }
		 }
		 
		 //更新cellStatus
		 for (x = 0 ; x < boardSize; x ++) {
			 for (y = 0; y < boardSize; y ++) {
				 if (isAliveInNextGeneration(x, y))
					 cellStatus[x][y] = true;
				 else
					 cellStatus[x][y] = false;
			 }
		 }
	}
	
	private boolean isAliveInNextGeneration(int x, int y) {
		//搜索8个格子 得出活邻居个数
		int aliveNeighbourCnt = 0;
		if (isPositionLegal(x - 1, y) && curGenerationStatus[x - 1][y])
			aliveNeighbourCnt ++;
		if (isPositionLegal(x - 1, y + 1) && curGenerationStatus[x - 1][y + 1])
			aliveNeighbourCnt ++;
		if (isPositionLegal(x - 1, y - 1) && curGenerationStatus[x - 1][y - 1])
			aliveNeighbourCnt ++;
		if (isPositionLegal(x, y + 1) && curGenerationStatus[x][y + 1])
			aliveNeighbourCnt ++;
		if (isPositionLegal(x, y - 1) && curGenerationStatus[x][y - 1])
			aliveNeighbourCnt ++;
		if (isPositionLegal(x + 1, y + 1) && curGenerationStatus[x + 1][y + 1])
			aliveNeighbourCnt ++;
		if (isPositionLegal(x + 1, y) && curGenerationStatus[x + 1][y])
			aliveNeighbourCnt ++;
		if (isPositionLegal(x + 1, y - 1) && curGenerationStatus[x + 1][y - 1])
			aliveNeighbourCnt ++;
		
		//根据当代情况和活邻居个数得出下代情况
		if (curGenerationStatus[x][y]) {//当代为活细胞
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
		if (x > 0 && x < boardSize && y > 0 && y < boardSize)
			return true;
		else
			return false;
	}
	
	public void clear() {
		
	}
	
	public Cell(int boardSize) {
		cellStatus = new boolean[boardSize][boardSize];
		this.boardSize = boardSize;
	}
	
	
}
