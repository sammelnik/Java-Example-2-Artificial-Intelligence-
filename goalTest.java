package sam;


import aima.core.search.framework.GoalTest;

public class goalTest implements GoalTest {

	@Override
	public boolean isGoalState(Object g) {
		gameBoard b = (gameBoard)g;
		return b.atGoal();
	}

}
