package br.com.faculdadedelta.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MotoristaYgorMendonca {

	private long id;
	private int idCat;
	private String nomeMotorista;
	private String destino;
	private double distancia;
	private Date dataCorrida;
	private double valorKm;
	private CnhYgorMendonca cnhYgorMendonca;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public String getNomeMotorista() {
		return nomeMotorista;
	}
	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	public Date getDataCorrida() {
		return dataCorrida;
	}
	public void setDataCorrida(Date dataCorrida) {
		this.dataCorrida = dataCorrida;
	}
	public double getValorKm() {
		return valorKm;
	}
	public void setValorKm(double valorKm) {
		this.valorKm = valorKm;
	}
	public CnhYgorMendonca getCnhYgorMendonca() {
		return cnhYgorMendonca;
	}
	public void setCnhYgorMendonca(CnhYgorMendonca cnhYgorMendonca) {
		this.cnhYgorMendonca = cnhYgorMendonca;
	}
	public MotoristaYgorMendonca(long id, int idCat, String nomeMotorista, String destino, double distancia,
			Date dataCorrida, double valorKm, CnhYgorMendonca cnhYgorMendonca) {
		super();
		this.id = id;
		this.idCat = idCat;
		this.nomeMotorista = nomeMotorista;
		this.destino = destino;
		this.distancia = distancia;
		this.dataCorrida = dataCorrida;
		this.valorKm = valorKm;
		this.cnhYgorMendonca = cnhYgorMendonca;
	}
	public MotoristaYgorMendonca() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MotoristaYgorMendonca other = (MotoristaYgorMendonca) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Double getDesconto() {

		Double desconto = 0.0;

		if (distancia > 600) {
			desconto = (valorKm * distancia) * 0.025;
		}
		return desconto;
	}

	public Double getDesconto2() {

		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		Date dr = null;
		Double desconto2 = 0.0;

		try {
			dr = sd.parse("01/12/2020");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (dataCorrida.after(dr)) {
			desconto2 = (valorKm * 0.01);
		}
		return desconto2;
	}

	public Double getValorTotal() {

		double total = ((valorKm * distancia)) - (getDesconto() + getDesconto2());
		return total;
	}
	
	}