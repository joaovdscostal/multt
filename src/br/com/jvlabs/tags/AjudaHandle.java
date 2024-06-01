package br.com.jvlabs.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AjudaHandle extends JvLabsHandle {

	private String text = "";

	protected Logger logger = Logger.getLogger(AjudaHandle.class);

	@Override
	public void doTag() throws JspException {

		JspWriter out = getJspContext().getOut();

		try {
			out.print(toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String html = "<i style=\"color: #004fd6;\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"" + text+ "\" class=\"fal fa-info-circle\"></i>";
		return html;
	}

}