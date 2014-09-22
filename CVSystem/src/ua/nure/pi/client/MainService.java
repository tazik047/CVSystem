package ua.nure.pi.client;

import java.util.Collection;

import ua.nure.pi.entity.Faculty;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("")
public interface MainService extends RemoteService {
	Collection<Faculty> getFaculties() throws IllegalArgumentException;
}
