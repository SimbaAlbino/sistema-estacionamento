package entidades;

import java.time.LocalDateTime;

import dadosEstacionamento.StatusCarro;
import dadosEstacionamento.VagaCarro;

public class PedidoEstacionar {
	
	private Carro carro;
	private String vaga;
	private StatusCarro statusCarro;
	private VagaCarro vagaCarro;
	
	private LocalDateTime start = LocalDateTime.now();
	private LocalDateTime finish;

	public PedidoEstacionar(Carro carro, String vaga, StatusCarro statusCarro, VagaCarro vagaCarro) {
		this.carro = carro;
		this.vaga = vaga;
		this.statusCarro = statusCarro;
		this.vagaCarro = vagaCarro;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public String getVaga() {
		return vaga;
	}

	public void setVaga(String vaga) {
		this.vaga = vaga;
	}

	public StatusCarro getStatusCarro() {
		return statusCarro;
	}

	public void setStatusCarro(StatusCarro statusCarro) {
		this.statusCarro = statusCarro;
	}

	public VagaCarro getVagaCarro() {
		return vagaCarro;
	}
	
	public void setVagaCarro(VagaCarro vagaCarro) {
		this.vagaCarro = vagaCarro;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getFinish() {
		return finish;
	}

	public void setFinish(LocalDateTime finish) {
		this.finish = finish;
	}

	
	
	
	/*
	 * private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	    
	    public long duration() {
	        long diff = checkOut.getTime() - checkIn.getTime();
	        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	    }

	    public void updateDates(Date checkIn, Date checkOut) {
	        this.checkIn = checkIn;
	        this.checkOut = checkOut;
	    }
	 */

}
