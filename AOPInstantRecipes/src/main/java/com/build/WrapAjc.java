package com.build;

import org.aspectj.bridge.*;
import org.aspectj.tools.ajc.Main;
import java.util.Arrays;

public class WrapAjc {
	public static void main(String[] args) {
		Main compiler = new Main();
		MessageHandler m = new MessageHandler();
		compiler.run(args, m);
		IMessage[] ms = m.getMessages(null, true);
		System.out.println("messages: " + Arrays.asList(ms));
	}
}
