package Graphs_final;

public class knapsack {
	private static Integer[] copyWithoutIndex(Integer[] list, int index) {
//		copy list
		Integer[] newList = new Integer[list.length - 1];
		for (int j=0; j<list.length; j++) {
			if (j != index) {
				newList[(j>index) ? j : j-1] = list[j];
			}
		}
		return newList;
	}
	
	public static boolean ks(Integer[] list, int target) {
//		Can improve by checking target at intermediate nodes
//		and removing obviously bad branches
		if (list.length == 0) {
			return target == 0;
		}
		boolean targetReached = false;
		int i = 0;
		while(i<list.length && !targetReached){
			Integer[] newList = copyWithoutIndex(list, i);
			targetReached = ks(newList, target - list[i]) || ks(newList, target);
			i++;
		}
		return targetReached;
	}
}
