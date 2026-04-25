package com.ding.feature.beans;

import java.util.List;

public class PaginatedBean<T> {
	private int code;
	private String status;
	private int pages;
	private int page;
	private int total;
	private int start;
	private int limit;
	private Iterable<T> data;

}
