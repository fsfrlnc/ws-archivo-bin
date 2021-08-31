package prueba.exception;

/**
 * Para avisar que parámetro es inválido (ejemplo: muy largo)
 * 
 * @author francisco
 *
 */
public class ParametroInvalidoException extends Exception
{

	public ParametroInvalidoException(String message)
	{
		super(message);
	}

}
