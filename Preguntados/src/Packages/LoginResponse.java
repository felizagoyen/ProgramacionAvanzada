package Packages;

import java.io.Serializable;

public class LoginResponse implements Serializable {

	private static final long serialVersionUID = 3832731611489649571L;
	private Integer tipoUsuario;
	
	public LoginResponse(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public Integer getTipoUsuario() {
		return tipoUsuario;
	}
	
}
