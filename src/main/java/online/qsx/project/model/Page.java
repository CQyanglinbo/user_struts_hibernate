package online.qsx.project.model;

import java.util.List;

public class Page<V> {
	//记录总条数
	private int totalCount;
	//取出的list集合
	private List<V> result;
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<V> getResult() {
		return result;
	}
	public void setResult(List<V> result) {
		this.result = result;
	}
	
}
