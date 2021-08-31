package prueba.dto;

/**
 * Representa una fila de la base de datos de operaciones.
 * 
 * @author francisco
 *
 */
public class OperacionDTO
{
	private long nrRegistro;
	private int nrOperacion;

	public OperacionDTO()
	{
	}

	public OperacionDTO(long nrRegistro, int nrOperacion)
	{
		super();
		this.nrRegistro = nrRegistro;
		this.nrOperacion = nrOperacion;
	}

	public long getNrRegistro()
	{
		return nrRegistro;
	}

	public void setNrRegistro(long nrRegistro)
	{
		this.nrRegistro = nrRegistro;
	}

	public int getNrOperacion()
	{
		return nrOperacion;
	}

	public void setNrOperacion(int nrOperacion)
	{
		this.nrOperacion = nrOperacion;
	}

}
