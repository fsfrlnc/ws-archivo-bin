package prueba.service;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Service;

import prueba.exception.NoEncontradoException;
import prueba.exception.ParametroInvalidoException;
import prueba.exception.TablasReferenciasException;
import prueba.util.byteUtil;

@Service
public class OperacionService
{

	private static String UBICACION_BD = "src/main/resources/bd-operaciones.bin";

	/**
	 * Entrega el número de operación correspondiente al número de registro del
	 * parámetro.
	 * 
	 * @param nrRegistroInput número de registro. hasta MAXIMO_BYTE_TARJETA de
	 *                        largo.
	 * @return nrOperacion, en caso que se encuentre.
	 * @throws NoEncontradoException
	 * @throws ParametroInvalidoException
	 */
	public int getNrOperacion(long nrRegistroInput) throws NoEncontradoException, ParametroInvalidoException
	{
		if (String.valueOf(nrRegistroInput).length() > byteUtil.MAXIMO_BYTE_TARJETA)
		{
			throw new ParametroInvalidoException("Número de Registro de largo inválido.");
		}

		int nrOperacionOutput = 0;

		String file = UBICACION_BD;
		DataInputStream reader;

		try
		{
			reader = new DataInputStream(new FileInputStream(file));
			int bytesTotales = reader.available();
			int cantidadRegistrosEsperada = bytesTotales / 9;

			System.out.println(">>> operacionService: getNrOperacion: bytes totales: " + bytesTotales);
			if (bytesTotales > 0)
			{
				for (int indexEnRevision = 1; indexEnRevision <= cantidadRegistrosEsperada; indexEnRevision++)
				{
					byte[] bytesLeidosNrRegistro = new byte[7];
					reader.read(bytesLeidosNrRegistro);
					long nrRegistroLeido = byteUtil.valorBytesToInt(bytesLeidosNrRegistro);
					System.out.println(">>> operacionService: getNrOperacion: Buscando: " + nrRegistroInput
							+ " nrRegistroLeido: " + nrRegistroLeido + " - indexEnRevision: " + indexEnRevision);

					byte[] bytesLeidosNrOperacion = new byte[2];
					reader.read(bytesLeidosNrOperacion);

					if (nrRegistroLeido == nrRegistroInput)
					{
						nrOperacionOutput = byteUtil.valor2bytesToInt(bytesLeidosNrOperacion);
						System.out.println(">>> operacionService: getNrOperacion: Encontrado: nrOperacionOutput: "
								+ nrOperacionOutput);
						break;
					}
				}
			}
			reader.close();
		} catch (FileNotFoundException e)
		{
			// Se supone que esto no debería ocurrir. 
			e.printStackTrace();
		} catch (IOException e)
		{
			// Se supone que esto no debería ocurrir.
			e.printStackTrace();
		} catch (TablasReferenciasException e)
		{
			// Se supone que esto no debería ocurrir. (avisos de largo de arreglo de byte equivocado)
			e.printStackTrace();
		}

		if (nrOperacionOutput > 0)
		{
			return nrOperacionOutput;
		} else
		{
			throw new NoEncontradoException("Registro no encontrado: " + nrRegistroInput);
		}

	}
}
