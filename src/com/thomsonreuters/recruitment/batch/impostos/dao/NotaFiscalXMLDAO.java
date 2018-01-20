package com.thomsonreuters.recruitment.batch.impostos.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.thomsonreuters.recruitment.batch.impostos.dao.parser.ControladorNotaFiscal;
import com.thomsonreuters.recruitment.batch.impostos.dao.parser.FormatadorEscritaLivroFiscal;
import com.thomsonreuters.recruitment.batch.impostos.dao.parser.FormatadorLeituraNotaFiscal;
import com.thomsonreuters.recruitment.batch.impostos.model.LivroFiscal;
import com.thomsonreuters.recruitment.batch.impostos.model.NotaFiscal;
import com.thomsonreuters.recruitment.batch.impostos.params.LeituraParametrosException;
import com.thomsonreuters.recruitment.batch.impostos.params.ParametrosExecucao;

/**
 * @author Wesley Menezes
 */
public class NotaFiscalXMLDAO implements NotaFiscalDAO {
  Path diretorioEntradaArquivoISS = null;
  Path diretorioSaidaProcessamentoISS = null;

  public NotaFiscalXMLDAO() throws LeituraParametrosException {
    diretorioEntradaArquivoISS = Paths
        .get(ParametrosExecucao.getInstance().getParametroExecucao(ParametrosExecucao._DIRETORIO_ENTRADA));
    diretorioSaidaProcessamentoISS = Paths
        .get(ParametrosExecucao.getInstance().getParametroExecucao(ParametrosExecucao._DIRETORIO_SAIDA));
  }

  public void createLivroFiscal(LivroFiscal livroFiscal) throws NotaFiscalDAOException {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(FormatadorEscritaLivroFiscal.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      FormatadorEscritaLivroFiscal formatadorEscritaLivroFiscal = new FormatadorEscritaLivroFiscal(livroFiscal);
      jaxbMarshaller.marshal( formatadorEscritaLivroFiscal, new File(diretorioSaidaProcessamentoISS+"/"+"livro-fiscal.xml" ) );
//      jaxbMarshaller.marshal(formatadorEscritaLivroFiscal, System.out);
    } catch (JAXBException je) {
      throw new NotaFiscalDAOException(je);
    }

  }

  /**
   * Carrega notas fiscais pendentes de processamento. Obs. Quando usa
   * persistencia XML são as disponíveis no diretorio de entrada (vide
   * recolhimentoiss.properties)
   * 
   * @throws IOException
   */
  public List<NotaFiscal> findNotaFiscalPendente() throws NotaFiscalDAOException {
    ControladorNotaFiscal contraladorNotas = new FormatadorLeituraNotaFiscal();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser;
    try {
      saxParser = factory.newSAXParser();
    } catch (ParserConfigurationException | SAXException e1) {
      throw new NotaFiscalDAOException("Falha na conversão dos arquivos.", e1);
    }
    try {
      List<File> arquivosISS = getNotaFiscalPendente(diretorioEntradaArquivoISS);
      // faz parse do arquivo
      for (File arquivoISS : arquivosISS) {
        try {
          saxParser.parse(arquivoISS, (DefaultHandler) contraladorNotas);
        } catch (Exception e) {
          System.out.println(e);
        }
      }
    } catch (Exception e) {
      throw new NotaFiscalDAOException("Falha na conversão dos arquivos.", e);
    }

    // processamento de recolhimento e consolidação de resultado
    return contraladorNotas.getNotas();
  }

  /**
   * 
   * Faz a leitura dos arquivos recebidos de NF para processamento. Obs. Pode
   * ser implementado o filter para tratar arquivos em função de algum atributo
   * específico.
   * 
   */
  private List<File> getNotaFiscalPendente(Path diretorioEntradaArquivoISS) throws IOException {

    List<File> arquivos = new ArrayList<File>();
    Path dir = FileSystems.getDefault().getPath(diretorioEntradaArquivoISS.toString());
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{xml}")) {

      for (Path path : stream) {
        arquivos.add(path.toFile());
      }

    } catch (IOException e) {
      throw e;
    }

    return arquivos;

  }

}
