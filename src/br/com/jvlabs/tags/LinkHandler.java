package br.com.jvlabs.tags;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.log4j.Logger;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LinkHandler extends JvLabsHandle {

	private String url = "";
	private String icone = "";
	private String texto = "";
	private String css = "";
	private Boolean button = false;

	protected Logger logger = Logger.getLogger(LinkHandler.class);

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

		if(!button) {
			String html = "<li class=\"nav-item "+css+" \">"
					+ "		<a href=\""+url+"\" class=\"nav-link \">"
					+ "			<i class=\""+icone+" mr-2  \"></i> "+texto+" "
					+ "		</a>"
					+ "</li>";

			return html;
		}else {
			String html = "<li class=\"nav-item "+css+" p-2 \">"
					+ "		<a href=\""+url+"\" class=\" btn btn-primary btn-block \">"
					+ "			<i class=\""+icone+" mr-2  \"></i> "+texto+" "
					+ "		</a>"
					+ "</li>";

			return html;
		}


	}

}