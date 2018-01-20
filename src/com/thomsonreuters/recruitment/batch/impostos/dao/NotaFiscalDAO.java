package com.thomsonreuters.recruitment.batch.impostos.dao;

import java.util.List;

import javax.xml.bind.JAXBException;

import com.thomsonreuters.recruitment.batch.impostos.model.LivroFiscal;
import com.thomsonreuters.recruitment.batch.impostos.model.NotaFiscal;

/**
 * @author Wesley Menezes
 * */

public interface NotaFiscalDAO {
  public void createLivroFiscal(LivroFiscal livroFiscal) throws NotaFiscalDAOException;
  public List<NotaFiscal> findNotaFiscalPendente() throws NotaFiscalDAOException;
}