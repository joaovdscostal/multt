package br.com.jvlabs.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubMenuHandler extends JvLabsHandle {

	private String icone = "";
	private String id = "";
	private String titulo = "";

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
		String content = getContent();
		String html = "";
		if(!content.trim().isEmpty()) {
		html = "<li class=\"nav-item\"> "
					+ "		<a class=\"nav-link collapsed\" href=\"#"+id+"\" data-toggle=\"collapse\" role=\"button\" aria-expanded=\"false\" aria-controls=\""+id+"\"> "
					+ "			<i class=\""+icone+" fa-fw mr-2\"></i> "
					+ "			<p class=\"toggle-text m-0\">"+titulo+"</p> "
					+ "		</a> "
					+ "		<div class=\"collapse\" id=\""+id+"\"> "
					+ "			<ul class=\"nav nav-sm flex-column\">"
					+ content
					+ "			</ul>"
					+ "		</div>"
					+ "</li>";
		}

		return html;
	}

	private Boolean hasValue(String string) {
		return string != null && !string.trim().isEmpty();
	}
}