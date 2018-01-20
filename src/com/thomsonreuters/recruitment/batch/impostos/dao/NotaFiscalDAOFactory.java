package com.thomsonreuters.recruitment.batch.impostos.dao;

import com.thomsonreuters.recruitment.batch.impostos.params.LeituraParametrosException;

/**
 * @author Wesley Menezes
 * */
public class NotaFiscalDAOFactory {

  public static final String _XML = "XML";
  
  public NotaFiscalDAOFactory() {
  }
  
  public NotaFiscalDAO getNotaFiscalDAO (String tipo) throws LeituraParametrosException {
    NotaFiscalDAO notaFiscalDAO = null;
    
    if (tipo.equals(_XML)) {
      notaFiscalDAO = new NotaFiscalXMLDAO();
    }
    return notaFiscalDAO;
  }

}
