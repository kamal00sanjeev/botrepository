package com.jira.bot.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.bot.model.BotResponse;

@RestController
@RequestMapping(value = "/bot")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:url.properties")
public class BotController {
	
	@Value("${jira.url}")
    private String jira_url;

	@GetMapping("/")
	public String checkEndpoint() {
		return "This Works fine!";
	}

	@PostMapping("/greetings")
	public BotResponse showGreetingsMessage(@RequestBody final BotResponse botResp) {
		if (botResp.getQueryText() == null || botResp.getQueryText().trim().isEmpty()) {
			botResp.setResponseText("Please type your query");
			return botResp;
		}
		String greetingsMsg = "Hello User, Welcome to Jira-Bot!";
		botResp.setResponseId(UUID.randomUUID());
		botResp.setResponseText(greetingsMsg);
		return botResp;
	}

	@PostMapping("/jiraUrl")
	public BotResponse getStaticJiraUrl(@RequestBody final BotResponse botResp) {
		if (botResp.getQueryText() == null || botResp.getQueryText().trim().isEmpty()) {
			botResp.setResponseText("Please type your query");
			return botResp;
		}
		botResp.setResponseId(UUID.randomUUID());
		botResp.setResponseText("Here is the Jira Url: \n\t" + jira_url);
		return botResp;
	}
	
	@PostMapping("/defaultMsg")
	public BotResponse getDefaultMessage(@RequestBody final BotResponse botResp) {
		if (botResp.getQueryText() == null || botResp.getQueryText().trim().isEmpty()) {
			botResp.setResponseText("Please type your query");
			return botResp;
		}
		botResp.setResponseId(UUID.randomUUID());
		botResp.setResponseText("Great!");
		return botResp;
	}

}
