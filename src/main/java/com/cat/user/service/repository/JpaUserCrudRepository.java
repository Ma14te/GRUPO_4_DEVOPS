package com.cat.user.service.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface JpaUserCrudRepository extends JpaRepository<UserEntity, UUID> {

	Optional<UserEntity> findByCorreoKey(String correoKey);
}
