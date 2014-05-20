/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moske;

/**
 *
 * @author LD
 */
public class OverflowException extends MachineException {
    private static final long serialVersionUID = 1L;

    public OverflowException() {
    }

    public OverflowException(String string) {
        super(string);
    }

    public OverflowException(Throwable thrwbl) {
        super(thrwbl);
    }

    public OverflowException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
    
}
