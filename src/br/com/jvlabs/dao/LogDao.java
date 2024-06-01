package br.com.jvlabs.dao;

import javax.enterprise.context.RequestScoped;

import br.com.jvlabs.model.Log;

@RequestScoped
public class LogDao  extends HibernateDao<Log> {

}
