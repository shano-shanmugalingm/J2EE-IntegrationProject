package com.senthur.api.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.senthur.service.delegate.NumberSequenceService;

/**
 * This class provides end points for Number Sequence REST Services.
 * 
 * @author Senthur Shanmugalingm
 * */
@Path("/service")
public class SequenceService {

	@Inject
	private NumberSequenceService numberSequenceService;

	/**
	 * This method provides provision for the user to send in the sequences. The sequences are send to a Queue to be processed at a latter time
	 * 
	 * @param param1
	 *            <code>int</code> the First Sequence
	 * @param param2
	 *            <code>int</code> the Second Sequence
	 * 
	 * @return <code>{@link Response}</code> containing a boolean result indicating success or failiure of the request.
	 * */
	@Path("/addNumbers")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response push(@QueryParam("param1") int param1, @QueryParam("param2") int param2) {
		boolean result = numberSequenceService.sendData(param1, param2);
		return Response.ok(result).build();
	}

	/**
	 * This method will list all the available sequences that are processed and stored in the database.
	 * 
	 * @return <code>{@link List}</code> of <code>{@link Integer}</code> of processed sequences.
	 * 
	 * */
	@Path("/list")
	@GET
	@Produces("application/json")
	public List<Integer> list() {
		return numberSequenceService.getAllAvialableNumberSequences();
	}
}
