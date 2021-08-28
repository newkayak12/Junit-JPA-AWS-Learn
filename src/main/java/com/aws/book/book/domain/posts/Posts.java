package com.aws.book.book.domain.posts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.aws.book.book.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500, nullable = false)
	private String title;

	@Column(columnDefinition = "text", nullable = false)
	private String content;

	private String author;

	/*
	 * @Setter를 주지 않는 이유 >> 규약상 있는게 맞지만, 인스턴스 값들이 어디서 언제 변해야하는지 구분할 수 없기 떄문에 선언하지
	 * 않는다. 특히 Entity클래스에서 Entity 클래스에서는 주로 생성자로 주입을 하는 식으로 사용
	 * 
	 */
	@Builder
	public Posts(String title, String content, String author) {

		this.title = title;
		this.content = content;
		this.author = author;
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + ":" + title + ":" + content + ":" + author;
	}
}
