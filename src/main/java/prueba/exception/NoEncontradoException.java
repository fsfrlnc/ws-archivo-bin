package prueba.exception;

/**
 * Para avisar que registro no se encontró.
 * 
 * @author francisco
 *
 */
public class NoEncontradoException extends Exception
{

	public NoEncontradoException(String message)
	{
		super(message);
	}

}
