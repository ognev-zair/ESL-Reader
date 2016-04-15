package com.artifex.mupdfdemo;

public class ChoosePDFItem {
	public enum Type {
		PARENT, DIR, DOC
	}

	final public Type type;
	final public String name;

	public ChoosePDFItem (Type t, String n) {
		type = t;
		name = n;
	}
}
