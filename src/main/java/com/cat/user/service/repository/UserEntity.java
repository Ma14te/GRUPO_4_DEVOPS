package com.cat.user.service.repository;

import java.util.UUID;

import com.cat.user.service.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "uk_users_correo_key", columnNames = "correo_key"))
class UserEntity {

	@Id
	private UUID id;

	@Column(nullable = false, length = 120)
	private String nombre;

	@Column(nullable = false, length = 120)
	private String apellido;

	@Column(nullable = false, length = 255)
	private String direccion;

	@Column(nullable = false, length = 40)
	private String telefono;

	@Column(nullable = false, length = 180)
	private String correo;

	@Column(name = "correo_key", nullable = false, length = 180)
	private String correoKey;

	protected UserEntity() {
	}

	private UserEntity(UUID id, String nombre, String apellido, String direccion, String telefono, String correo,
			String correoKey) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.correoKey = correoKey;
	}

	static UserEntity fromDomain(User user, String correoKey) {
		return new UserEntity(user.getId(), user.getNombre(), user.getApellido(), user.getDireccion(),
				user.getTelefono(), user.getCorreo(), correoKey);
	}

	User toDomain() {
		return User.builder()
				.id(id)
				.nombre(nombre)
				.apellido(apellido)
				.direccion(direccion)
				.telefono(telefono)
				.correo(correo)
				.build();
	}
}
