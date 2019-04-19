package main;

import org.opencv.core.Core;

public class Main {
	String fileName;
	
	public void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
}
