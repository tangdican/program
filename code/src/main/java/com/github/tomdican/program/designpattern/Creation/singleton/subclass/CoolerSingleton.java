package com.github.tomdican.program.designpattern.Creation.singleton.subclass;

public class CoolerSingleton extends Singleton {
	// useful instance variables here
	protected static CoolerSingleton uniqueInstance;
 
	private CoolerSingleton() {
		super();
	}

}
