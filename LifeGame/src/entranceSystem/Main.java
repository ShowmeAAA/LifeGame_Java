package entranceSystem;

import uiSystem.MainFrame;
import uiSystem.BoardPanel;
import businessSystem.Cell;
import businessSystem.MyTimer;

public class Main {
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame("LifeGame");
		MyTimer myTimer = new MyTimer(500, mainFrame.getBoardPanel());
		myTimer.startTimer();
	}
}
