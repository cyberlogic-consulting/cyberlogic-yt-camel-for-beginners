package ch.cyberlogic.camel.examples.model.cxf;

import jakarta.xml.ws.WebFault;
import java.io.Serial;

@WebFault(name = "NoSuchPet")
public class NoSuchPetException extends Exception {

	@Serial
	private static final long serialVersionUID = 1L;

	private final String faultInfo;

	public NoSuchPetException(Long id) {
		super("Pet \"" + id + "\" does not exist.");
		this.faultInfo = "Pet \"" + id + "\" does not exist.";
	}

	public NoSuchPetException(String name) {
		super("Pet \"" + name + "\" does not exist.");
		this.faultInfo = "Pet \"" + name + "\" does not exist.";
	}

	public NoSuchPetException(String message, String faultInfo) {
		super(message);
		this.faultInfo = faultInfo;
	}

	public NoSuchPetException(String message, String faultInfo, Throwable cause) {
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	public String getFaultInfo() {
		return this.faultInfo;
	}
}
