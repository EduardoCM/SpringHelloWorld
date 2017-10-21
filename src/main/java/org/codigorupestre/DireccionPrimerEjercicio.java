package org.codigorupestre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//Candidata a ser inyectada
@Component
public class DireccionPrimerEjercicio {

	private String calle;
	private String cp;

	public DireccionPrimerEjercicio() {

	}

	public DireccionPrimerEjercicio(String calle, String cp) {
		super();
		this.calle = calle;
		this.cp = cp;
	}

	@Autowired
	public void setCalle(@Value("Encino") String calle) {
		this.calle = calle;
	}

	@Autowired
	public void setCp(@Value("54260") String cp) {
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", cp=" + cp + "]";
	}

}
