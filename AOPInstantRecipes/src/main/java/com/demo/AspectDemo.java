package com.demo;

public class AspectDemo {

	public void method1(String name) {
	}

	public boolean method2(String name) {
		return true;
	}

	public void method3(String name) {

		throw new RuntimeException("Aspect Demo");
	}

	public static void main(String[] args) {
		AspectDemo myDemo = new AspectDemo();
		System.out.println("method1: ");
		myDemo.method1("AOPRecipes1");
		System.out.println("method2: ");
		myDemo.method2("AOPRecipes2");
		System.out.println("method3: ");
		myDemo.method3("AOPRecipes3");

	}

}
