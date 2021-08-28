package com.aws.book.book.config.auth.dto;

import java.util.Map;

import com.aws.book.book.domain.user.Role;
import com.aws.book.book.domain.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey, name, email, picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email,
			String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
			Map<String, Object> attributes) {
		if ("naver".equals(registrationId)) {
			return ofNaver("id", attributes);
		}

		return ofGoogle(userNameAttributeName, attributes);
	}

	public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder().name((String) attributes.get(("name")))
				.email((String) attributes.get(("email"))).picture((String) attributes.get("picture"))
				.attributes(attributes).nameAttributeKey(userNameAttributeName).build();
	}

	public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> response = (Map<String, Object>) attributes.get("response");

		log.warn("===={}", response);
		return OAuthAttributes.builder().name((String) response.get("name")).email((String) response.get("id"))
				.picture((String) response.get("profile_image")).attributes(response)
				.nameAttributeKey(userNameAttributeName).build();
	}

	public User toEntity() {
		return User.builder().name(name).email(email).picture(picture).role(Role.GUEST).build();
	}

}
