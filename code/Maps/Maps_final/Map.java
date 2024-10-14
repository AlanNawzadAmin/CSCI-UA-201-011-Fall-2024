package Maps_final;
import LinkedList_final.List;

public interface Map<K, V> {
	int size();
	boolean isEmpty();
	V get(K key);
	V put(K key, V value);
	V remove(K key);
	
	List<K> keySet();
	List<V> values();
	List<Entry<K,V>> entrySet();
}