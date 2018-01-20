package com.thomsonreuters.recruitment.batch.impostos.dao.parser;

import java.util.List;

import com.thomsonreuters.recruitment.batch.impostos.model.NotaFiscal;

/**
 * Classe auxiliar para garantir exposição/visibilidade adequada
 * 
 * @author Wesley Menezes
 * */
public interface ControladorNotaFiscal {

  List<NotaFiscal> getNotas();

}