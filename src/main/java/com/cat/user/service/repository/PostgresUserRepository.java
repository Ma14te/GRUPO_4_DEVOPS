package com.cat.user.service.repository;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cat.user.service.domain.User;
import com.cat.user.service.exceptions.DuplicateUserException;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostgresUserRepository implements UserRepository {

	private final JpaUserCrudRepository jpaRepository;

	@Override
	@Transactional
	public User save(User user) {
		String correoKey = normalizeCorreo(user.getCorreo());
		if (jpaRepository.findByCorreoKey(correoKey).isPresent()) {
			throw new DuplicateUserException(user.getCorreo());
		}
		try {
			return jpaRepository.saveAndFlush(UserEntity.fromDomain(user, correoKey)).toDomain();
		}
		catch (DataIntegrityViolationException ex) {
			throw new DuplicateUserException(user.getCorreo());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(UUID id) {
		return jpaRepository.findById(id).map(UserEntity::toDomain);
	}

	private static String normalizeCorreo(String correo) {
		return correo.trim().toLowerCase(Locale.ROOT);
	}
}
