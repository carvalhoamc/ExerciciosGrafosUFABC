// Introdu��o � Programa��o Orientada a Objetos Usando Java, p303

package ep0.exemplos;

import ep0.Keyboard;

/**
 * A classe UsaKeyboard ilustra usos dos m�todos est�ticos na classe
 * Keyboard para leitura (a partir do teclado ou de arquivos de resposta) de
 * valores de tipos nativos e de inst�ncias da classe String.
 */
public class UsaKeyboard
  {
  /**
   * O m�todo main permite a execu��o desta classe. Este m�todo ilustra
   * usos dos m�todos est�ticos na classe Keyboard.
   * @param argumentos Os argumentos que podem ser passados para o m�todo via
   *                   linha de comando, mas que neste caso ser�o ignorados.
   */
   public static void main(String[] argumentos)
    {
    // Vamos imprimir mensagens de erros e avisos ou n�o?
    System.out.print("Deseja ver mensagens de erro e avisos? [s/n]: ");
    // L� um booleano e o usa para mostrar (ou n�o) as mensagens.
    Keyboard.setDebug(Keyboard.readBoolean(false));
    // Leitura de valores booleanos.
    // true, t, yes, y, on, v, s, sim, verdadeiro s�o sin�nimos de true.
    // false, f, no, n, off, nao, n�o, falso s�o sin�nimos de false.
    boolean b1, b2;
    System.out.print("Entre um valor booleano ou Enter para [true]: ");
    b1 = Keyboard.readBoolean();
    System.out.print("Entre um valor booleano ou Enter para [false]: ");
    b2 = Keyboard.readBoolean(false);
    System.out.println("Voc� entrou ["+b1+"] e ["+b2+"].");
    // Leitura de valores do tipo char.
    char c1, c2;
    System.out.print("Entre um caractere ou Enter para [espa�o]: ");
    c1 = Keyboard.readChar();
    System.out.print("Entre um caractere ou Enter para ['s']: ");
    c2 = Keyboard.readChar('s');
    System.out.println("Voc� entrou ["+c1+"] e ["+c2+"].");
    // Leitura de valores do tipo byte.
    byte by1, by2;
    System.out.print("Entre um valor do tipo byte ou Enter para [0]: ");
    by1 = Keyboard.readByte();
    System.out.print("Entre um valor do tipo byte ou Enter para [-123]: ");
    by2 = Keyboard.readByte((byte)-123);  // Cast � necess�rio!
    System.out.println("Voc� entrou ["+by1+"] e ["+by2+"].");
    // Leitura de valores do tipo short.
    short s1, s2;
    System.out.print("Entre um valor do tipo short ou Enter para [0]: ");
    s1 = Keyboard.readShort();
    System.out.print("Entre um valor do tipo short ou Enter para [-9999]: ");
    s2 = Keyboard.readShort((short)-9999);  // Cast � necess�rio!
    System.out.println("Voc� entrou ["+s1+"] e ["+s2+"].");
    // Leitura de valores do tipo int.
    int i1, i2;
    System.out.print("Entre um valor do tipo int ou Enter para [0]: ");
    i1 = Keyboard.readInt();
    System.out.print("Entre um valor do tipo int ou Enter para [123456]: ");
    i2 = Keyboard.readInt(123456);  
    System.out.println("Voc� entrou ["+i1+"] e ["+i2+"].");
    // Leitura de valores do tipo long.
    long l1, l2;
    System.out.print("Entre um valor do tipo long ou Enter para [0]: ");
    l1 = Keyboard.readLong();
    System.out.print("Entre um valor do tipo long ou Enter para [-1000]: ");
    l2 = Keyboard.readLong(-1000);  // Argumento int ser� convertido para long.
    System.out.println("Voc� entrou ["+l1+"] e ["+l2+"].");
    // Leitura de valores do tipo float.
    float f1, f2;
    System.out.print("Entre um valor do tipo float ou Enter para [0]: ");
    f1 = Keyboard.readFloat();
    System.out.print("Entre um valor do tipo float ou Enter para [1e23]: ");
    f2 = Keyboard.readFloat(1e23f);
    System.out.println("Voc� entrou ["+f1+"] e ["+f2+"].");
    // Leitura de valores do tipo double.
    double d1, d2;
    System.out.print("Entre um valor do tipo double ou Enter para [0]: ");
    d1 = Keyboard.readDouble();
    System.out.print("Entre um valor do tipo double ou Enter para [1e-20]: ");
    d2 = Keyboard.readDouble(1e-20);
    System.out.println("Voc� entrou ["+d1+"] e ["+d2+"].");
    // Leitura de inst�ncias da classe String.
    String st1, st2;
    System.out.print("Entre uma String ou pressione Enter para [] (vazia): ");
    st1 = Keyboard.readString();
    System.out.print("Entre uma String ou pressione Enter para [Java!]: ");
    st2 = Keyboard.readString("Java!");
    System.out.println("Voc� entrou ["+st1+"] e ["+st2+"].");
    } 
  }
