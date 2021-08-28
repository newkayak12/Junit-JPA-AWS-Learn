package com.aws.book.book.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class PostsUpdateRequestDto {
	private String title, content;

	@Builder
	public PostsUpdateRequestDto(String title, String content) {
		this.title = title;
		this.content = content;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return title + ":" + content;
	}

}
