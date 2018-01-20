package com.thomsonreuters.recruitment.batch.impostos.params;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Wesley Menezes
 * */
public class ParametrosExecucao {
  private static final String _PARAMS_RECOLHIMENTOISS_PROPERTIES = "./params/recolhimentoiss.properties";
  public static final String _DIRETORIO_SAIDA = "diretorioSaida";
  public static final String _DIRETORIO_ENTRADA = "diretorioEntrada";
  public static ParametrosExecucao INSTANCE = null;
  private Map<String, String> parametros = new HashMap<String, String>();

  private ParametrosExecucao() throws LeituraParametrosException {
    // carga de parâmetros do sistema
    Properties systemProperties = null;

    try {
      systemProperties = leArquivoParametros();
      parametros.put(_DIRETORIO_ENTRADA, systemProperties.getProperty(_DIRETORIO_ENTRADA));
      parametros.put(_DIRETORIO_SAIDA, systemProperties.getProperty(_DIRETORIO_SAIDA));
    } catch (IOException e) {
      throw new LeituraParametrosException("Falha na leitura arquivo ["+_PARAMS_RECOLHIMENTOISS_PROPERTIES+"]. Verifique se o arquivo existe e se estah configurado corretamente.",e);
    }
  }
  
  public static ParametrosExecucao getInstance() throws LeituraParametrosException {
    //considerando single-thread...
    if (INSTANCE == null){
      INSTANCE = new ParametrosExecucao();
    }
    return INSTANCE;
  }

  private static Properties leArquivoParametros() throws IOException {
    Properties properties = new Properties();
    InputStream input = null;

    input = new FileInputStream(_PARAMS_RECOLHIMENTOISS_PROPERTIES);

    // carrega arquivo de propriedades
    properties.load(input);

    input.close();

    return properties;
  }

  public String getParametroExecucao(String parametro) {
    return parametros.get(parametro);
  }

}
