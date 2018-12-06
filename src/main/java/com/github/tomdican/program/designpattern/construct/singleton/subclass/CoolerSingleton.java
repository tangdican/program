package com.github.tomdican.program.designpattern.construct.singleton.subclass;

public class CoolerSingleton extends Singleton {
	// useful instance variables here
	protected static CoolerSingleton uniqueInstance;
 
	private CoolerSingleton() {
		super();
	}

}
