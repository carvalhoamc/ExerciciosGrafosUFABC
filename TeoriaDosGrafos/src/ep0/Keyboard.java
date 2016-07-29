/* Esta classe � usada para entrar dados via teclado.
   Ela foi extraida do livro do Rafael Santos:
   Introdu��o � Programa��o Orientada a Objetos Usando Java, p292.
*/
//package keyboard;

package ep0;

/* As seguintes linhas permitem o uso das classes de entrada e sa�da e
   de processamento de tokens */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
/**
 * A classe <tt>Keyboard</tt> permite a leitura de dados de tipos nativos e de
 * int�ncias da classe <tt>String</tt> a partir do teclado. Basicamente esta
 * classe encapsula o funcionamento do Stream <tt>System.in</tt>, que pode ser
 * usado como entrada padr�o de dados.
 * <p>
 * Esta classe � baseada na classe <tt>EasyIn</tt>, de Peter van der Linden.
 * As principais modifica��es foram:
 * <ul>
 * <li>Os m�todos s�o est�ticos para facilitar o uso da classe.
 * <li>Cria��o de m�todos sobrecarregados para que valores <i>default</i>
 * possam ser usados.
 * <li>Melhor tratamento da exce��es.
 * <li>Reconhecimento de v�rios valores como booleanos e capacidade de leitura
 * de <tt>NaNs</tt> e infinitos para valores dos tipos <tt>float</tt> e 
 * <tt>double</tt>.
 * <li>Descri��o dos detalhes de implementa��o com coment�rios.
 * </ul>
 * A classe permite o uso de arquivos de respostas (<i>"answer files"</i>),
 * que s�o arquivos contendo os dados que deveriam ser entrados pelo teclado.
 * A entrada destes dados pode ser simulada atrav�s de redirecionamento de
 * arquivos. Se, por exemplo, tivermos uma aplica��o (classe)
 * <tt>UsaDataKeyboard</tt> e quisermos usar um arquivo de respostas chamado
 * <tt>dados.txt</tt> que j� cont�m os dados que devem ser digitados, podemos
 * executar o comando <tt>java UsaDataKeyboard &lt; dados.txt </tt> para ler
 * os dados do arquivo em vez do teclado. O comando ser� o mesmo para todos os
 * sistemas operacionais que possuam terminal de linha de comando.
 * <p>
 * O suporte a arquivos de respostas � limitado, somente funcionar� se todos
 * os dados a serem digitados forem entrados no arquivo de respostas, um em
 * cada linha. Exce��es ocorrer�o caso estas regras n�o sejam seguidas.
 * <p>
 * A mensagem original de copyright de Peter van der Linden � mostrada ao final
 * desta listagem. Vale a pena notar que a sintaxe proposta e mostrada na
 * mensagem � diferente da usada nesta classe.
 * <p>
 * O livro <b>"An Introduction to Computer Science Using Java"</b>, de Samuel
 * N. Kamin, M. Dennis Mickunas e Edward M. Reingold (editora McGraw-Hill,
 * ISBN 0-07-034224-5) tamb�m apresenta uma classe <tt>Keyboard</tt>, cujo
 * funcionamento e c�digo s�o diferentes desta.
 *
 * @autor Rafael Santos
 * @version 1.3
 */
public class Keyboard
  {
  // Declara��o dos atributos privados desta classe.
  // O atributo is � uma inst�ncia da classe InputStreamReader que ser�
  // constru�da a partir do Stream System.in. Note que j� que esta classe s�
  // tem m�todos est�ticos, n�o ter� construtor, mas podemos inicializar
  // inst�ncias de classes da forma mostrada a seguir.
  private static InputStreamReader is = new InputStreamReader(System.in);
  // O atributo br � uma inst�ncia da classe BufferedReader que usa is como
  // argumento para seu construtor.
  private static BufferedReader br = new BufferedReader(is);
  // O atributo st � uma inst�ncia da classe StringTokenizer que ser� usada em
  // v�rios m�todos nesta classe.
  private static StringTokenizer st;
  // O atributo nt � uma inst�ncia da classe String que ser� usada em v�rios
  // m�todos nesta classe.
  private static String nt;
  // O atributo debug estabelece se mensagens de erro de convers�o e leitura
  // devem ser mostradas ou n�o. O atributo n�o pode ser modificado diretamente,
  // somente atrav�s de m�todos espec�ficos desta classe.
  private static boolean debug = false;
  
  /**
   * O m�todo <tt>getToken</tt> l� uma String do <tt>BufferedReader</tt>
   * associado com a entrada padr�o e devolve uma inst�ncia de
   * <tt>StringTokenizer</tt> contendo os <i>tokens</i> criados a partir da
   * linha lida. O m�todo � declarado como <tt>private</tt> pois s� dever� ser
   * chamado a partir de outros m�todos da classe.
   * @return Uma inst�ncia de <tt>StringTokenizer</tt> contendo os
   *         <i>tokens</i> obtidos da linha lida.
   * @exception IOException se um erro de entrada e sa�da ocorrer.
   * @exception NullPointerException se uma String vazia for lida e tentarmos
   *            construir uma inst�ncia de <tt>StringTokenizer</tt> com ela.
   * @see java.util.StringTokenizer <tt>StringTokenizer</tt>
   */
  private static StringTokenizer getToken() throws IOException, 
      NullPointerException
    {
    String s = br.readLine();
    return new StringTokenizer(s);
    }

  /**
   * O m�todo <tt>readBoolean</tt> l� e devolve um valor do tipo
   * <tt>boolean</tt>. Este m�todo simplesmente chama o m�todo
   * <tt>readBoolean</tt> com argumentos, descrito a seguir, considerando o
   * valor <i>default</i> como sendo <tt>true</tt>.
   * @return O valor do tipo <tt>boolean</tt> lido (ou, em caso de erro 
   *         de leitura, <tt>true</tt>).
   */
  public static boolean readBoolean() 
    {
    return readBoolean(true);
    }

  /**
   * O m�todo <tt>readBoolean</tt> l� e devolve um valor do tipo
   * <tt>boolean</tt>. Este m�todo tenta pegar o pr�ximo <i>token</i> a ser
   * lido da entrada padr�o e o compara com algumas Strings constantes para
   * verificar a igualdade, devolvendo <tt>true</tt> ou <tt>false</tt>
   * dependendo do resultado da compara��o.
   * @param defaultValue O valor <i>default</i> caso n�o seja poss�vel ler e
   *        decodificar um valor booleano.
   * @return O valor do tipo <tt>boolean</tt> lido (ou, em caso de erro 
   *         de leitura, igual ao argumento passado para o m�todo).
   */
  public static boolean readBoolean(boolean defaultValue) 
    {
    try
      {
      st = getToken();  // L� os tokens da linha,
      nt = st.nextToken(); // e usa a primeira String dos tokens.
      // Devolve true se a String for igual a...
      if (nt.equalsIgnoreCase("true") ||  
          nt.equalsIgnoreCase("t") ||  
          nt.equalsIgnoreCase("yes") ||  
          nt.equalsIgnoreCase("y") ||  
          nt.equalsIgnoreCase("on") ||  
          nt.equalsIgnoreCase("v") ||  
          nt.equalsIgnoreCase("s") ||  
          nt.equalsIgnoreCase("sim") ||  
          nt.equalsIgnoreCase("verdadeiro"))
        return true;
      // Devolve false se a String for igual a...
      else if (nt.equalsIgnoreCase("false") ||  
                nt.equalsIgnoreCase("f") ||  
                nt.equalsIgnoreCase("no") ||  
                nt.equalsIgnoreCase("n") ||  
                nt.equalsIgnoreCase("off") ||  
                nt.equalsIgnoreCase("nao") ||  
                nt.equalsIgnoreCase("n�o") ||  
                nt.equalsIgnoreCase("falso"))
        return false;
      // (Altera��o: 2016/04/19) 0: false; 1: true
      if (nt.startsWith("0"))
        return false;
      else if (nt.startsWith("1") || nt.startsWith("2") || nt.startsWith("3") || nt.startsWith("4") || nt.startsWith("5") || nt.startsWith("6") || nt.startsWith("7") || nt.startsWith("8") || nt.startsWith("9"))
        return true;
      // Se n�o for nenhum dos valores reconhecidos, devolve o default.
      else
        return defaultValue;
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um "+
                           "boolean. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se n�o houver tokens,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada n�o cont�m um boolean. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O m�todo <tt>readByte</tt> l� e devolve um valor do tipo <tt>byte</tt>.
   * Este m�todo simplesmente chama o m�todo <tt>readByte</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>(byte)0</tt>.
   * @return O valor do tipo <tt>byte</tt> lido (ou, em caso de erro de
   *         leitura, <tt>(byte)0</tt>).
   */
  public static byte readByte() 
    {
    return readByte((byte) 0);
    }

  /**
   * O m�todo <tt>readByte</tt> l� e devolve um valor do tipo <tt>byte</tt>.
   * Este m�todo tenta pegar o pr�ximo <i>token</i> a ser lido da entrada
   * padr�o e o passa como argumentos para o m�todo <tt>parseByte</tt> da
   * classe <tt>Byte</tt>, que tenta convert�-lo. Se a leitura e convers�o
   * puderem ser feitas, um valor do tipo <tt>byte</tt> � devolvido, caso
   * contr�rio o valor <i>default</i> (passado como argumento) � devolvido.
   * @param defaultValue O valor <i>default</i> caso n�o seja poss�vel ler e
   *        decodificar um valor do tipo <tt>byte</tt>.
   * @return O valor do tipo <tt>byte</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o m�todo).
   */
  public static byte readByte(byte defaultValue) 
    {
    try
      {
      st = getToken();  // L� os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o valor processado pela classe Byte.
      return Byte.parseByte(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um "+
                           "byte. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de convers�o,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um byte. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se n�o houver tokens,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada n�o cont�m um byte. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O m�todo <tt>readShort</tt> l� e devolve um valor do tipo <tt>short</tt>.
   * Este m�todo simplesmente chama o m�todo <tt>readShort</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>(short)0</tt>.
   * @return O valor do tipo <tt>short</tt> lido (ou, em caso de erro de
   *         leitura, <tt>(short)0</tt>).
   */
  public static short readShort() 
    {
    return readShort((short) 0);
    }

  /**
   * O m�todo <tt>readShort</tt> l� e devolve um valor do tipo <tt>short</tt>.
   * Este m�todo tenta pegar o pr�ximo <i>token</i> a ser lido da entrada
   * padr�o e o passa como argumentos para o m�todo <tt>parseShort</tt> da
   * classe <tt>Short</tt>, que tenta convert�-lo. Se a leitura e convers�o
   * puderem ser feitas, um valor do tipo <tt>short</tt> � devolvido, caso
   * contr�rio o valor <i>default</i> (passado como argumento) � devolvido.
   * @param defaultValue O valor <i>default</i> caso n�o seja poss�vel ler e
   *        decodificar um valor do tipo <tt>short</tt>.
   * @return O valor do tipo <tt>short</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o m�todo).
   */
  public static short readShort(short defaultValue) 
    {
    try
      {
      st = getToken();  // L� os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o valor processado pela classe Short.
      return Short.parseShort(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um "+
                           "short. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de convers�o,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um short. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se n�o houver tokens,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada n�o cont�m um short. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O m�todo <tt>readInt</tt> l� e devolve um valor do tipo <tt>int</tt>.
   * Este m�todo simplesmente chama o m�todo <tt>readInt</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>0</tt>.
   * @return O valor do tipo <tt>int</tt> lido (ou, em caso de erro de
   *         leitura, <tt>0</tt>).
   */
  public static int readInt() 
    {
    return readInt(0);
    }

  /**
   * O m�todo <tt>readInt</tt> l� e devolve um valor do tipo <tt>int</tt>.
   * Este m�todo tenta pegar o pr�ximo <i>token</i> a ser lido da entrada
   * padr�o e o passa como argumentos para o m�todo <tt>parseInt</tt> da
   * classe <tt>Integer</tt>, que tenta convert�-lo. Se a leitura e convers�o
   * puderem ser feitas, um valor do tipo <tt>int</tt> � devolvido, caso
   * contr�rio o valor <i>default</i> (passado como argumento) � devolvido.
   * @param defaultValue O valor <i>default</i> caso n�o seja poss�vel ler e
   *        decodificar um valor do tipo <tt>int</tt>.
   * @return O valor do tipo <tt>int</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o m�todo).
   */
  public static int readInt(int defaultValue) 
    {
    try
      {
      st = getToken();  // L� os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o valor processado pela classe Integer.
      return Integer.parseInt(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um "+
                           "int. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de convers�o,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um int. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se n�o houver tokens,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada n�o cont�m um int. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O m�todo <tt>readLong</tt> l� e devolve um valor do tipo <tt>long</tt>.
   * Este m�todo simplesmente chama o m�todo <tt>readLong</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>0l</tt>.
   * @return O valor do tipo <tt>long</tt> lido (ou, em caso de erro de
   *         leitura, <tt>0l</tt>).
   */
  public static long readLong() 
    {
    return readLong(0l);
    }

  /**
   * O m�todo <tt>readLong</tt> l� e devolve um valor do tipo <tt>long</tt>.
   * Este m�todo tenta pegar o pr�ximo <i>token</i> a ser lido da entrada
   * padr�o e o passa como argumentos para o m�todo <tt>parseLong</tt> da
   * classe <tt>Long</tt>, que tenta convert�-lo. Se a leitura e convers�o
   * puderem ser feitas, um valor do tipo <tt>long</tt> � devolvido, caso
   * contr�rio o valor <i>default</i> (passado como argumento) � devolvido.
   * @param defaultValue O valor <i>default</i> caso n�o seja poss�vel ler e
   *        decodificar um valor do tipo <tt>long</tt>.
   * @return O valor do tipo <tt>long</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o m�todo).
   */
  public static long readLong(long defaultValue) 
    {
    try
      {
      st = getToken();  // L� os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o valor processado pela classe Long.
      return Long.parseLong(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um "+
                           "long. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de convers�o,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um long. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se n�o houver tokens,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada n�o cont�m um long. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O m�todo <tt>readFloat</tt> l� e devolve um valor do tipo <tt>float</tt>.
   * Este m�todo simplesmente chama o m�todo <tt>readFloat</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>0f</tt>.
   * @return O valor do tipo <tt>float</tt> lido (ou, em caso de erro de
   *         leitura, <tt>0f</tt>).
   */
  public static float readFloat() 
    {
    return readFloat(0f);
    }

  /**
   * O m�todo <tt>readFloat</tt> l� e devolve um valor do tipo <tt>float</tt>.
   * Este m�todo tenta pegar o pr�ximo <i>token</i> a ser lido da entrada
   * padr�o e o analisa, verificando se aparentemente � <tt>NaN</tt> ou
   * infinito. Se n�o for, passa o valor lido como argumento para o m�todo
   * <tt>parseFloat</tt> da classe <tt>Float</tt>, que tenta convert�-lo.
   * Se a leitura e convers�o puderem ser feitas, um valor do tipo <tt>float</tt>
   * � devolvido, caso contr�rio o valor <i>default</i> (passado como argumento)
   * � devolvido.
   * @param defaultValue O valor <i>default</i> caso n�o seja poss�vel ler e
   *        decodificar um valor do tipo <tt>float</tt>.
   * @return O valor do tipo <tt>float</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o m�todo).
   */
  public static float readFloat(float defaultValue) 
    {
    try
      {
      st = getToken();  // L� os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      if (nt.toLowerCase().startsWith("nan"))
        return Float.NaN;
      else if (nt.toLowerCase().startsWith("inf"))
        return Float.POSITIVE_INFINITY;
      else if (nt.toLowerCase().startsWith("+inf"))
        return Float.POSITIVE_INFINITY;
      else if (nt.toLowerCase().startsWith("-inf"))
        return Float.NEGATIVE_INFINITY;
      else  // Devolve o valor processado pela classe Float.
        return Float.parseFloat(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um "+
                           "float. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de convers�o,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um float. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se n�o houver tokens,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada n�o cont�m um float. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O m�todo <tt>readDouble</tt> l� e devolve um valor do tipo <tt>double</tt>.
   * Este m�todo simplesmente chama o m�todo <tt>readDouble</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>0.0</tt>.
   * @return O valor do tipo <tt>double</tt> lido (ou, em caso de erro de
   *         leitura, <tt>0.0</tt>).
   */
  public static double readDouble() 
    {
    return readDouble(0.0);
    }

  /**
   * O m�todo <tt>readDouble</tt> l� e devolve um valor do tipo <tt>double</tt>.
   * Este m�todo tenta pegar o pr�ximo <i>token</i> a ser lido da entrada
   * padr�o e o analisa, verificando se aparentemente � <tt>NaN</tt> ou
   * infinito. Se n�o for, passa o valor lido como argumento para o m�todo
   * <tt>parseDouble</tt> da classe <tt>Double</tt>, que tenta convert�-lo.
   * Se a leitura e convers�o puderem ser feitas, um valor do tipo <tt>double</tt>
   * � devolvido, caso contr�rio o valor <i>default</i> (passado como argumento)
   * � devolvido.
   * @param defaultValue O valor <i>default</i> caso n�o seja poss�vel ler e
   *        decodificar um valor do tipo <tt>double</tt>.
   * @return O valor do tipo <tt>double</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o m�todo).
   */
  public static double readDouble(double defaultValue) 
    {
    try
      {
      st = getToken();  // L� os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      if (nt.toLowerCase().startsWith("nan"))
        return Double.NaN;
      else if (nt.toLowerCase().startsWith("inf"))
        return Double.POSITIVE_INFINITY;
      else if (nt.toLowerCase().startsWith("+inf"))
        return Double.POSITIVE_INFINITY;
      else if (nt.toLowerCase().startsWith("-inf"))
        return Double.NEGATIVE_INFINITY;
      else  // Devolve o valor processado pela classe Double.
      return Double.parseDouble(nt);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um "+
                           "double. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NumberFormatException nfe)  // Se houver erro de convers�o,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro convertendo "+nt+" para "+
                           "um double. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se n�o houver tokens,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada n�o cont�m um double. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O m�todo <tt>readChar</tt> l� e devolve um valor do tipo <tt>char</tt>.
   * Este m�todo simplesmente chama o m�todo <tt>readChar</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>' '</tt> (espa�o em branco).
   * @return O valor do tipo <tt>char</tt> lido (ou, em caso de erro de
   *         leitura, um espa�o em branco).
   */
  public static char readChar() 
    {
    return readChar(' ');
    }

  /**
   * O m�todo <tt>readChar</tt> l� e devolve um valor do tipo <tt>char</tt>.
   * Este m�todo tenta pegar o pr�ximo <i>token</i> a ser lido da entrada
   * padr�o e devolve o primeiro caractere deste <i>token</i>, ignorando os
   * outros caracteres. Se a leitura puder ser feita, um valor do tipo
   * <tt>char</tt> � devolvido, caso contr�rio o valor <i>default</i>
   * (passado como argumento) � devolvido.
   * @param defaultValue O valor <i>default</i> caso n�o seja poss�vel ler e
   *        decodificar um valor do tipo <tt>char</tt>.
   * @return O valor do tipo <tt>char</tt> lido (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o m�todo).
   */
  public static char readChar(char defaultValue) 
    {
    try
      {
      st = getToken();  // L� os tokens da linha,
      nt = st.nextToken();  // e usa a primeira String dos tokens.
      // Devolve o primeiro caractere da String lida.
      return nt.charAt(0);
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um "+
                           "char. Devolve "+defaultValue);
       return defaultValue;
      }
    catch (NoSuchElementException nsee)  // Se n�o houver tokens,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Entrada n�o cont�m um char. "+
                           "Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O m�todo <tt>readString</tt> l� e devolve uma inst�ncia da classe <tt>String</tt>.
   * Este m�todo simplesmente chama o m�todo <tt>readString</tt> com argumentos,
   * descrito a seguir, considerando o valor <i>default</i> como sendo
   * <tt>""</tt> (String vazia).
   * @return O valor da <tt>String</tt> lida (ou, em caso de erro de
   *         leitura, uma String vazia).
   */
  public static String readString() 
    {
    return readString("");
    }

  /**
   * O m�todo <tt>readString</tt> l� e devolve uma inst�ncia da classe <tt>String</tt>.
   * Este m�todo devolve uma linha inteira lida da entrada
   * padr�o, sem process�-la como <i>tokens</i>. Se a leitura 
   * puder ser feita, a <tt>String</tt> lida � devolvida, caso
   * contr�rio o valor <i>default</i> (passado como argumento) � devolvido.
   * O valor <i>default</i> tamb�m � devolvido se a String digitada for vazia.
   * @param defaultValue O valor <i>default</i> caso n�o seja poss�vel ler 
   *        uma inst�ncia da classe <tt>String</tt>.
   * @return O valor da <tt>String</tt> lida (ou, em caso de erro de
   *         leitura, igual ao argumento passado para o m�todo).
   */
  public static String readString(String defaultValue) 
    {
    try
      {
      nt = br.readLine();  // Uma String diretamente do teclado.
      // Se nada foi digitado, ent�o devolve o valor default.
      if (nt.trim().length() == 0)
        return defaultValue;
      else
        return nt;  // Devolve o que foi lido do teclado.
      }
    catch (IOException ioe)  // Se houver algum erro de leitura,
      {
      if (debug)  // Se for pedida a impress�o de mensagens de erro,
        System.err.println("KEYBOARD:: Erro de entrada e sa�da lendo um "+
                           "String. Devolve "+defaultValue);
       return defaultValue;
      }
    }

  /**
   * O m�todo <tt>setDebut</tt> modifica o atributo que indica que mensagens de
   * erro dever�o ser mostradas na tela.
   * @param show Se for true mostra mensagens, caso contr�rio n�o as mostra.
   */
  public static void setDebug(boolean show) 
    {
    debug = show;
    if (debug)
        System.err.println("KEYBOARD:: Mostrando mensagens de erro e avisos.");
    }

/*

Segue a mensagem original da classe EasyIn de Peter van der Linden

Simple input from the keyboard for all primitive types. ver 1.0
Copyright (c) Peter van der Linden, May 5 1997.
corrected error message 11/21/97

The creator of this software hereby gives you permission to:

1. copy the work without changing it
2. modify the work providing you send me a copy which I can use in any way I want, including incorporating into this work.
3. distribute copies of the work to the public by sale, lease, rental, or lending
4. perform the work
5. display the work
6. fold the work into a funny hat and wear it on your head.

This is not thread safe, not high performance, and doesn't tell EOF.
It's intended for low-volume easy keyboard input.
An example of use is:

EasyIn easy = new EasyIn();
int i = easy. readInt();  // reads an int from System.in
float f = easy.readFloat();  // reads a float from System.in


*/

  }
