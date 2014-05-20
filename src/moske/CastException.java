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
public class CastException extends MachineException {
    private static final long serialVersionUID = 1L;

    public CastException() {
    }

    public CastException(String string) {
        super(string);
    }

    public CastException(Throwable thrwbl) {
        super(thrwbl);
    }

    public CastException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
    
}
