package com.jira.client;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatBotUsingRestAPI extends JFrame implements KeyListener {
	private static final long serialVersionUID = -2042689387185105550L;
	JPanel p = new JPanel();
	JTextArea dialog = new JTextArea(20, 50);
	JTextArea input = new JTextArea(2, 50);
	JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	public static void main(String[] args) {
		new ChatBotUsingRestAPI();
	}

	public ChatBotUsingRestAPI() {
		super("Jira ChatBot");
		setSize(600, 450);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		dialog.setEditable(false);
		input.addKeyListener(this);

		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(211, 211, 211));
		add(p);
		setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(false);
			// grab user input
			String quote = input.getText();
			input.setText("");
			addText("[ You ]::\t" + quote);
			String userQuery = quote.trim();

			List<String> greetingEntity = Arrays.asList("Hi", "hi", "Hello", "hello", "Greetings", "Good morning");
			List<String> defaultEntiry = Arrays.asList("OK", "ok", "Good", "good", "Fine", "fine");
			String botResponse = null;
			if (greetingEntity.contains(userQuery)) {
				botResponse = BotResponseUtil.postUserResponse("greetings");
			} else if (userQuery.contains("url")) {
				botResponse = BotResponseUtil.postUserResponse("jiraUrl");
			} else if (defaultEntiry.contains(userQuery)) {
				botResponse = BotResponseUtil.postUserResponse("defaultMsg");
			} else {
				botResponse = "There is no matching response from Bot. Try again!";
			}

			addText("\n[ Jita-Bot ]::\t" + botResponse);
			addText("\n");
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void addText(String str) {
		dialog.setText(dialog.getText() + str);
	}
}
