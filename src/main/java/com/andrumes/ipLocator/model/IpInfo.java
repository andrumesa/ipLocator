package com.andrumes.ipLocator.model;

public class IpInfo extends LocatorRequest
{
    public IpInfo(String ip,Boolean banned) 
    {		
    	this.setIp(ip);
    	this.setBanned(banned);
    	this.setTimeInmillis(System.currentTimeMillis());
	}
    
    public IpInfo(String ip) 
    {		
    	this.setIp(ip);
    	this.setTimeInmillis(System.currentTimeMillis());
    	this.setBanned(true);
	}

	private Integer hits=0;
	private long timeInmillis;
	private boolean isBanned;
public Integer getHits() {
	return hits;
}

public void setHits(Integer hits) {
	this.hits = hits;
}

public long getTimeInmillis() {
	return timeInmillis;
}

public void setTimeInmillis(long timeInmillis) {
	this.timeInmillis = timeInmillis;
}

public boolean isBanned() {
	return isBanned;
}

public void setBanned(boolean isBanned) {
	this.isBanned = isBanned;
}
}
