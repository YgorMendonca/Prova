package br.com.faculdadedelta.converter;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.faculdadedelta.dao.CnhDAOYgorMendonca;
import br.com.faculdadedelta.modelo.CnhYgorMendonca;

@FacesConverter(value = "cnhConverter")
public class CnhConverterYgorMendonca implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		
		if (valor != null) {
			CnhDAOYgorMendonca dao = new CnhDAOYgorMendonca();
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {
		
		if (valor != null) {
			return String.valueOf(((CnhYgorMendonca)valor).getId());
		}
		
		return null;
	}

}