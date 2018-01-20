package com.thomsonreuters.recruitment.batch.impostos.dao.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thomsonreuters.recruitment.batch.impostos.model.Nota;
import com.thomsonreuters.recruitment.batch.impostos.model.NotaFiscal;

/**
 * Classe responsável por fazer o parser da Nota Fiscal emitida
 * 
 * @author Wesley Menezes
 * */
public class FormatadorLeituraNotaFiscal extends DefaultHandler implements ControladorNotaFiscal {

  private static final String VALOR_TOTAL = "valor_total";
  private static final String CODIGO_SERVICO = "codigo_servico";
  private static final String DATA_EMISSAO = "data_emissao";
  private static final String NUMERO2 = "numero";
  private static final String EMITENTE_CNPJ = "emitente_cnpj";

  boolean emitenteNota = false;
  boolean numero = false;
  boolean dataEmissao = false;
  boolean codigoServico = false;
  boolean valorTotal = false;

  // List to hold Employees object
  private List<NotaFiscal> notas = null;
  private Nota nota = null;

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (qName.equalsIgnoreCase("nota")) {
      nota = new Nota();
      // inicializa lista de forma thread-safe
      if (notas == null)
        notas = Collections.synchronizedList(new ArrayList<NotaFiscal>());
    } else if (qName.equalsIgnoreCase(EMITENTE_CNPJ)) {
      emitenteNota = true;
    } else if (qName.equalsIgnoreCase(NUMERO2)) {
      numero = true;
    } else if (qName.equalsIgnoreCase(DATA_EMISSAO)) {
      dataEmissao = true;
    } else if (qName.equalsIgnoreCase(CODIGO_SERVICO)) {
      codigoServico = true;
    } else if (qName.equalsIgnoreCase(VALOR_TOTAL)) {
      valorTotal = true;
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (qName.equalsIgnoreCase("nota")) {
      notas.add(nota);
    }
  }

  @Override
  public void characters(char ch[], int start, int length) throws SAXException {
    if (emitenteNota) {
      nota.setEmitente(new String(ch, start, length));
      emitenteNota = false;
    } else if (numero) {
      nota.setNumero(new String(ch, start, length));
      numero = false;
    } else if (dataEmissao) {
      nota.setDataEmissao(new String(ch, start, length));
      dataEmissao = false;
    } else if (codigoServico) {
      nota.setCodigoServico(new Integer(new String(ch, start, length)));
      codigoServico = false;
    } else if (valorTotal) {
      nota.setValorTotal(Double.parseDouble(new String(ch, start, length)));
      valorTotal = false;
    }
  }

  /* (non-Javadoc)
   * @see com.thomsonreuters.recruitment.batch.impostos.factory.CarregadorNotas#getNotas()
   */
  @Override
  public List<NotaFiscal> getNotas() {
    return notas;
  }
}