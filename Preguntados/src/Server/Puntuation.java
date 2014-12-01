package Server;

public class Puntuation {
	private final int STANDARD_PUNTUATION=10;
	private Integer puntuacion;
	private Integer partidasJugadas;
	private Integer partidasGanadas;
	private Integer partidasPerdidas;
	private Integer preguntasCorrectas;
	private Integer preguntasIncorrectas;

	public Puntuation(Integer puntuacion, Integer partidasJugadas,
			Integer partidasGanadas, Integer partidasPerdidas,
			Integer preguntasCorrectas, Integer preguntasIncorrectas) {
		this.puntuacion = puntuacion;
		this.partidasJugadas = partidasJugadas;
		this.partidasGanadas = partidasGanadas;
		this.partidasPerdidas = partidasPerdidas;
		this.preguntasCorrectas = preguntasCorrectas;
		this.preguntasIncorrectas = preguntasIncorrectas;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public Integer getPartidasJugadas() {
		return partidasJugadas;
	}

	public Integer getPartidasGanadas() {
		return partidasGanadas;
	}

	public Integer getPartidasPerdidas() {
		return partidasPerdidas;
	}

	public Integer getPreguntasCorrectas() {
		return preguntasCorrectas;
	}

	public Integer getPreguntasIncorrectas() {
		return preguntasIncorrectas;
	}

	public void upPuntuacion() {
		this.puntuacion += STANDARD_PUNTUATION;
	}

	public void upPartidasJugadas() {
		this.partidasJugadas++;
	}

	public void upPartidasGanadas() {
		this.partidasGanadas++;
	}

	public void upPartidasPerdidas() {
		this.partidasPerdidas++;
	}

	public void upPreguntasCorrectas() {
		this.preguntasCorrectas++;
		this.upPuntuacion();
	}

	public void upPreguntasIncorrectas() {
		this.preguntasIncorrectas++;
	}
	
	
	
}
