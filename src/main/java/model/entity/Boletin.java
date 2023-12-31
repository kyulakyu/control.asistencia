package model.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "boletin")
public class Boletin {
	// se crean los atributos de la clase Boletin
	@Id
	@Column(name = "id")
	private int idBoletin;
	private String titulo;
	private String fechaBoletin;
	private String detalle;

	// se crea el constructor con todos los atributos de la clase
	public Boletin() {

	}

	public Boletin(int idBoletin, String titulo, String fechaBoletin, String detalle) {
		super();
		this.idBoletin = idBoletin;
		this.titulo = titulo;
		this.fechaBoletin = fechaBoletin;
        this.detalle = detalle;

	}
	// se crean los metodos de acceso y modificadores de yodos los atributos
	public int getIdBoletin() {
		return idBoletin;
	}

	public boolean setIdBoletin(int idBoletin) {
		String idBoletinString = Integer.toString(idBoletin);

		if (idBoletinString != null && !idBoletinString.isEmpty()) {
			try {
				int id = Integer.parseInt(idBoletinString);
				this.idBoletin = id;
				return true; // Indicar que el valor se estableció correctamente
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFechaBoletin() {
		return fechaBoletin;
	}

	public boolean setFechaBoletin(String fechaBoletin) {
		if (fechaBoletin == null) {
			return false; // La fecha es nula, formato incorrecto
		}
		String formatoFecha = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
		sdf.setLenient(false); // Evita la flexibilidad en el análisis de fechas
		try {
			sdf.parse(fechaBoletin);
			this.fechaBoletin = fechaBoletin;
			return true; // La fecha tiene el formato correcto y fue establecida correctamente
		} catch (ParseException e) {
			return false; // La fecha no tiene el formato correcto
		}
	}

	
    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}