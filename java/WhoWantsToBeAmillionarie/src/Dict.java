public class Dict {
	String[] keys;
	int[] values;
	
	public Dict() {
		keys = new String[0];
		values = new int[0];
	}
	
	public int GetLength() {
		return values.length;
	}
	
	public void SetKey(String key, int value) {
		boolean contained = false;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].equals(key)) {
				values[i] = value;
				contained = true;
				break;
			}
		}
		if (!contained) {
			String[] oldKeys = keys;
			int[] oldValues = values;
			
			keys = new String[GetLength() + 1];
			values = new int[GetLength() + 1];
			
			for (int i = 0; i < GetLength() - 1; i++) {
				keys[i] = oldKeys[i];
				values[i] = oldValues[i];
			}
			keys[GetLength() - 1] = key;
			values[GetLength() - 1] = value;
		}
	}
	
	public int GetValue(String key) {
		for (int i = 0; i < GetLength(); i++) {
			if (keys[i].equals(key)) {
				return values[i];
			}
		}
		System.out.println(">>>ERROR: key can not be found in Dict!, GetValue() method returns 0 now!>>>");
		return 0;
	}
	
	public String GetKeyByValue(int value) {
		for (int i = 0; i < GetLength(); i++) {
			if (values[i] == value) {
				return keys[i];
			}
		}
		System.out.println(">>>ERROR: value can not be found in Dict!, GetKeyByValue() method returns null now!>>>");
		return null;
	}
	
	public void SortByKeys() {
		for (int i = 1; i < GetLength(); i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (keys[j + 1].compareTo(keys[j]) < 0) {
					String tempKey = keys[j];
					keys[j] = keys[j + 1];
					keys[j + 1] = tempKey;
					
					int tempValue = values[j];
					values[j] = values[j + 1];
					values[j + 1] = tempValue;
				}
				else break;
			}
		}
	}
	
	public boolean ContainsKey(String key) {
		for (int i = 0; i < GetLength(); i++) {
			if (keys[i].equals(key)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean ContainsValue(int value) {
		for (int i = 0; i < GetLength(); i++) {
			if (values[i] == value) {
				return true;
			}
		}
		return false;
	}
	
	public String[] GetKeysArray() {
		return keys;
	}
	
	public int[] GetValuesArray() {
		return values;
	}
}
