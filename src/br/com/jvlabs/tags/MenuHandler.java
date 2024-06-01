package br.com.jvlabs.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MenuHandler extends JvLabsHandle {

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
			html = "<ul class=\"navbar-nav\">" + content + "</ul>";
		}

		return html;
	}

}