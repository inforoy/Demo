package gob.pe.proc.capadatos;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable,UserDetails {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="dni",nullable=false)
	private String dni;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="rol_id_rol")
	private Rol rol;
	@Column(name="nombres",nullable=false)
	private String nombres;
	@Column(name="apaterno",nullable=false)
	private String apaterno;
	@Column(name="amaterno",nullable=false)
	private String amaterno;
	
	@Column(name="fecha_nacimiento",nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;
	@Column(name="correo")
	private String correo;
	@Column(name="direccion")
	private String direccion;
	@Column(name="telefono")
	private String telefono;

	@Column(name="username",nullable=false)
	private String username;
	@Transient
	private String confirmarPassname;
	
	@Column(name="passname",nullable=false)
	private String passname;
	@Column(name="fecha_ingreso",nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaIngreso;
	@Column(name="fecha_salida")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaSalida;
	@Column(name="estado_laboral",nullable=false)
	private boolean estadoLaboral=true;

	@OneToMany(mappedBy="id.usuario",cascade=CascadeType.PERSIST)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Asignado> asignados;
	@Transient
	private Collection<GrantedAuthority> perfiles = new HashSet<GrantedAuthority>();
	public Set<Asignado> getAsignados() {
		return asignados;
	}

	public void setAsignados(Set<Asignado> asignados) {
		this.asignados = asignados;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApaterno() {
		return this.apaterno;
	}

	public void setApaterno(String apaterno) {
		this.apaterno = apaterno;
	}

	public String getAmaterno() {
		return this.amaterno;
	}

	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassname() {
		return this.passname;
	}

	public void setPassname(String passname) {
		this.passname = passname;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public boolean isEstadoLaboral() {
		return this.estadoLaboral;
	}

	public void setEstadoLaboral(boolean estadoLaboral) {
		this.estadoLaboral = estadoLaboral;
	}
	
	public String getConfirmarPassname() {
		return confirmarPassname;
	}

	public void setConfirmarPassname(String confirmarPassname) {
		this.confirmarPassname = confirmarPassname;
	}

	public Collection<? extends GrantedAuthority> getPerfiles() {
		return perfiles;
	}

	@SuppressWarnings("unchecked")
	public void setPerfiles(List<? extends GrantedAuthority> perfiles) {
		this.perfiles = (List<GrantedAuthority>) perfiles;
	}

	//metodos de UserDetails
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfiles;
	} 
	public String getPassword() {
		return passname;
	}
	public boolean isAccountNonExpired() {
		return true;
	}
	public boolean isAccountNonLocked() {
		return true;
	}
	public boolean isCredentialsNonExpired() {
		return true;
	}
	public boolean isEnabled() {
		return true;
	}
}
