package com.andrumes.ipLocator.handlers;


import com.andrumes.ipLocator.model.LocatorResponse;

public abstract class LocatorHandler {
	
	private LocatorHandler nextHandler;	
	
	
	public abstract  boolean process(LocatorResponse locatorResponse);

	public LocatorHandler getNextHandler() {
		return nextHandler;
	}

	public void setNextHandler(LocatorHandler nextHandler) {

		this.nextHandler = nextHandler;
	}


	
}
