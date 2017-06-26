package org.thinker.openapi;

import java.io.Serializable;

public class ApiKeyVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String hostName;
	private String apiKey;
	private int count;
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hostName == null) ? 0 : hostName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiKeyVO other = (ApiKeyVO) obj;
		if (hostName == null) {
			if (other.hostName != null)
				return false;
		} else if (!hostName.equals(other.hostName))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "ApiKeyVO [hostName=" + hostName + ", apiKey=" + apiKey
				+ ", count=" + count + "]";
	}
	
	
	
}
