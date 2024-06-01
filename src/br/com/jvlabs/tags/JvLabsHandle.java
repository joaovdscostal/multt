package br.com.jvlabs.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class JvLabsHandle extends SimpleTagSupport{


	protected String getContent() {
		StringWriter sw = new StringWriter();

		try {
			getJspBody().invoke(sw);
		} catch (JspException | IOException e) {
			e.printStackTrace();
		}
		String content = sw.toString();
		return content;
	}
}
