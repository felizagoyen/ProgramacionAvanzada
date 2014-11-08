package Packages;

import java.io.Serializable;

public class LoginResponse implements Serializable {

	private static final long serialVersionUID = 3832731611489649571L;
	private Boolean usuarioValido;
	private Integer tipoUsuario;
	
	public LoginResponse(Boolean usuarioValido, Integer tipoUsuario) {
		this.usuarioValido = usuarioValido;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Boolean getUsuarioValido() {
		return usuarioValido;
	}
	
	public Integer getTipoUsuario() {
		return tipoUsuario;
	}
	
}
