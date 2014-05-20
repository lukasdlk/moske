/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package os;

/**
 *
 * @author adomas
 */
public class Utils {
    public static char intToChar(int integer) throws CastException {
        if (integer<0 || 9<integer) {
            throw new CastException("Invalid cast int->char ("+integer+").");
        }
        return (char)(integer+48);
    }
    public static int charToInt(char character) throws CastException {
        if(character<'0' || character>'9') {
            throw new CastException("Invalid cast char->int ("+character+").");
        }
        return (int)(character)-48;
    }
    public static byte intToByte(int integer) throws CastException {
        if(integer>255) {
            throw new CastException("Invalid cast int->byte");
        }
        return (byte)integer;
    }
    public static int byteToInt(byte b) {
        int result = (int)b;
        if(result<0)
            result =256+result;
        return result;
    }
    public static char byteToChar(byte b) {
        return (char)b;
    }
    public static byte charToByte(char character) throws CastException {
        if(character>((char)255)){
            throw new CastException("Invalid cast char->byte");
        }
        return (byte)character;
    }
    public static int addWithOverflow(int a, int b) throws OverflowException {
        long result = ((long)a)+b;
        if(result>Integer.MAX_VALUE || result<Integer.MIN_VALUE) {
            throw new OverflowException("Overflow occured.");
        }
        return (int)result;
    }
    public static int subWithOverflow(int a, int b) throws OverflowException {
        return addWithOverflow(a, -b);
    }
    public static int mulWithOverflow(int a, int b) throws OverflowException {
        long result = ((long)a)*b;
        if(result>Integer.MAX_VALUE || result<Integer.MIN_VALUE) {
            throw new OverflowException("Overflow occured.");
        }
        return (int)result;
    }
    
    public static int wordToInt(byte array[], int startpoz) {
        int result = 0;
        for(int i=0; i<4; i++) {
            result *=256;
            result +=byteToInt(array[startpoz+i]);
        }
        return result;
    }
    public static void intToWord(int integer, byte array[], int startpoz) throws CastException {
        for(int i=3; i>=0; i--) {
            array[startpoz+i] = intToByte(integer%256);
            integer /=256;
        }
    }
}
