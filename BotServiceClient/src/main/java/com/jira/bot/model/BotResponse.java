package com.jira.bot.model;

import java.io.Serializable;
import java.util.UUID;

public class BotResponse implements Serializable {
	private static final long serialVersionUID = 7357690258081939215L;
	private UUID responseId;
	private String queryText;
	private String responseText;

	public BotResponse() {
	}

	public UUID getResponseId() {
		return responseId;
	}

	public void setResponseId(UUID responseId) {
		this.responseId = responseId;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	
}
