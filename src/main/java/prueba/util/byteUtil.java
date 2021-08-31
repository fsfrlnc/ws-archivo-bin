package prueba.util;

import java.nio.ByteBuffer;
import java.util.Stack;

import prueba.exception.TablasReferenciasException;

public class byteUtil
{
	public static final int MAXIMO_BYTE_TARJETA = 7;

	/**
	 * Metodo que obtiene el numero en long de un arreglo de MAXIMO_BYTE_TARJETA. Se
	 * modifica para que nombre de método no indique obligatoriamente 7 bytes, sino
	 * que se deja abierto a cualquier largo y se valida largo máximo internamente
	 * segùn MAXIMO_BYTE_TARJETA.
	 * 
	 * @param leido arreglo de byte leido con el numero de la tarjeta
	 * @return numero de tarjeta
	 * @throws TablasReferenciasException Arreglo debe ser de MAXIMO_BYTE_TARJETA.
	 */
	public static final long valorBytesToInt(final byte[] leidoIn) throws TablasReferenciasException
	{
		byte[] leido = invertirCadena(leidoIn);
		final int largoValor = MAXIMO_BYTE_TARJETA;
		if (leido.length != largoValor)
		{
			throw new TablasReferenciasException("Arreglo debe ser de " + MAXIMO_BYTE_TARJETA + " bytes.");
		}
		final byte largoLong = 8;
		byte[] bytes = new byte[largoLong];
		final int indiceValor = 1;
		for (int i = 0; i < largoValor; i++)
		{
			bytes[indiceValor + i] = leido[0 + i];
		}
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes);
		buffer.flip(); // need flip
		return buffer.getLong();
	}

	/**
	 * Metodo que obtiene el numero en int de un arreglo de 2 bytes.
	 * 
	 * @param leido arreglo de byte leido con el numero de la tarjeta
	 * @return codigo de producto
	 * @throws TablasReferenciasException TablasReferenciasException
	 */
	public static int valor2bytesToInt(final byte[] leidoIn) throws TablasReferenciasException
	{
		byte[] leido = invertirCadena(leidoIn);
		final int largoValor = 2;
		if (leido.length != largoValor)
		{
			throw new TablasReferenciasException("Arreglo debe ser de 2 bytes.");
		}
		final byte largoInt = 4;
		byte[] bytes = new byte[largoInt];
		final int indiceValor = 2;
		bytes[indiceValor] = leido[0];
		bytes[indiceValor + 1] = leido[1];
		ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES);
		byteBuffer.put(bytes);
		byteBuffer.flip();
		return byteBuffer.getInt();
	}

	/**
	 * invierte cadena de bytes.
	 * 
	 * @author francisco
	 * @param cadenaBytes arreglo de bytes de entrada
	 * @return arreglo de bytes invertido
	 */
	public static byte[] invertirCadena(byte[] cadenaBytes)
	{
		byte[] cadenaInvertida = new byte[cadenaBytes.length];

		Stack<Byte> stackTemporal = new Stack<Byte>();
		for (int i = 0; i < cadenaBytes.length; i++)
		{
			stackTemporal.push(cadenaBytes[i]);
		}

		int j = 0;
		while (!stackTemporal.empty())
		{
			cadenaInvertida[j] = stackTemporal.pop();
			j++;
		}

		return cadenaInvertida;
	}
}
