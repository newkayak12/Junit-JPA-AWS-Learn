package com.aws.book.book.service;

import java.util.List;
import java.util.stream.Collectors;

import com.aws.book.book.domain.posts.Posts;
import com.aws.book.book.domain.posts.PostsRepository;
import com.aws.book.book.web.dto.PostsListResponseDto;
import com.aws.book.book.web.dto.PostsResponseDto;
import com.aws.book.book.web.dto.PostsSaveRequestDto;
import com.aws.book.book.web.dto.PostsUpdateRequestDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

		return new PostsResponseDto(entity);
	}

	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc().stream().map(posts -> new PostsListResponseDto(posts))
				.collect(Collectors.toList());
	}

	@Transactional
	public void delete(Long id) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

		postsRepository.delete(posts);
	}
}
