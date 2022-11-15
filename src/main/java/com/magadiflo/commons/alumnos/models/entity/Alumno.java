package com.magadiflo.commons.alumnos.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "alumnos")
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellido;

	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@Lob // Permite persistir un objeto largo, es tipo de contenido object.
	@JsonIgnore // No lo mostraremos en el Json, ya que es un binario muy extenso, en su reemplazo creamos el método getFotoHashCode(), que mostrará el código hash de la foto en el JSON
	private byte[] foto;

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	// getFotoHashCode(), lo iniciamos con get, para que
	// se serialice con el json, es decir se genere como un atributo
	public Integer getFotoHashCode() {
		// Todos los objetos de java tienen un hashCode (un identificador)
		// que lo hace único, que le permite comparar en el método equals
		// (las instancias)
		return (this.foto != null) ? this.foto.hashCode() : null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		return this.id != null && this.id.equals(other.getId());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Alumno [id=");
		builder.append(id);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", apellido=");
		builder.append(apellido);
		builder.append(", email=");
		builder.append(email);
		builder.append(", createAt=");
		builder.append(createAt);
		builder.append("]");
		return builder.toString();
	}

}
