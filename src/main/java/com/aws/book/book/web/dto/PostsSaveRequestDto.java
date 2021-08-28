package com.aws.book.book.web.dto;

import com.aws.book.book.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class PostsSaveRequestDto {

	private String title, content, author;

	public Posts toEntity() {
		return Posts.builder().title(title).content(content).author(author).build();
	}

	@Builder
	public PostsSaveRequestDto(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;

	}
}
