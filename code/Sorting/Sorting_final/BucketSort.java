package Sorting_final;

import Maps_final.Entry;

public class BucketSort {
	public static <V> void bucketSort(Entry<Integer, V>[] array) {
		int max = 0;
		for (int i = 0; i<array.length; i++) {
			max = Math.max(max, array[i].getKey());
		}
		
		Entry<Integer, V>[] new_array = (Entry<Integer, V>[])(new Object[max+1]);
		for (int i = 0; i<array.length; i++) {
			new_array[array[i].getKey()] = array[i];
		}
		
		int pos_in_arr = 0;
		for (int i = 0; i<new_array.length; i++) {
			if (new_array[i] != null){
				array[pos_in_arr] = new_array[i];
				pos_in_arr++;
			}
		}
	}
}
