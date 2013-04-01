package com.appspot.omega;

import java.util.ArrayList;

public class SearchResult {
	String query;
	ArrayList<String> suggestions;
	ArrayList<String> data;
	public SearchResult(String query, ArrayList<String> suggestions, ArrayList<String> data) {
		super();
		this.query = query;
		this.suggestions = suggestions;
		this.data = data;
	}
}
