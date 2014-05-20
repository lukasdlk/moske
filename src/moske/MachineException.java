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
public class MachineException extends Exception {
    private static final long serialVersionUID = 1L;

    public MachineException(String string) {
        super(string);
    }

    public MachineException() {
    }

    public MachineException(Throwable thrwbl) {
        super(thrwbl);
    }

    public MachineException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
