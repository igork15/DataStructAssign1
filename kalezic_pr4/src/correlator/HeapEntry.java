package correlator;

import java.util.Comparator;



public class HeapEntry<AnyKey, AnyValue> implements Comparator<AnyValue>{
    public AnyKey data;
    public AnyValue value;
    public HeapEntry<AnyKey, AnyValue> next;
    public Comparator comparator;

    public HeapEntry(AnyKey data) {
        this(data,null, null);
    }

    public HeapEntry(AnyKey data, AnyValue value, HeapEntry<AnyKey, AnyValue> next) {
        this.data = data;
        this.next = next;
        this.value = value;
    }

	@Override
	public int compare(AnyValue data, AnyValue otherData) {
		// TODO Auto-generated method stub
		return comparator.compare(data, otherData);
	}




}
