package prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import prueba.dto.OperacionDTO;
import prueba.exception.NoEncontradoException;
import prueba.exception.ParametroInvalidoException;
import prueba.service.OperacionService;

@RestController
public class OperacionController
{
	@Autowired
	OperacionService operacionService;

	/**
	 * Retorna nrRegistro y nrOperacion, siesque se encuentra en la bd.
	 * 
	 * @param operacionDTO JSON con nrRegistro especificado.
	 * @return operacionDTO JSON con nrRegistro y nrOperacion especificados.
	 */
	@PostMapping(path = "/operaciones/", consumes = "application/json", produces = "application/json")
	public OperacionDTO getOperacionDTOporJSON(@RequestBody OperacionDTO operacionDTO)
	{
		System.out.println(">>> /operaciones/ - JSON: nrRegistro: " + operacionDTO.getNrRegistro() + "}");
		try
		{
			operacionDTO.setNrOperacion(operacionService.getNrOperacion(operacionDTO.getNrRegistro()));
		} catch (NoEncontradoException e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (ParametroInvalidoException e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}

		return operacionDTO;
	}

	/**
	 * Retorna nrRegistro y nrOperacion, siesque se encuentra en la bd. Versión que
	 * permite consultar usando nrRegistro en path.
	 * 
	 * @param nrRegistro entero correspondiente al número de registro.
	 * @return operacionDTO JSON con nrRegistro y nrOperacion.
	 */
	@GetMapping(path = "/operaciones/{nrRegistro}", produces = "application/json")
	public OperacionDTO getOperacionDTOporVariablePath(@PathVariable int nrRegistro)
	{
		System.out.println(">>> /operaciones/{" + nrRegistro + "}");
		OperacionDTO operacionDTO = new OperacionDTO();
		operacionDTO.setNrRegistro(nrRegistro);
		try
		{
			operacionDTO.setNrOperacion(operacionService.getNrOperacion(nrRegistro));
		} catch (NoEncontradoException e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (ParametroInvalidoException e)
		{
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		}

		return operacionDTO;
	}
}