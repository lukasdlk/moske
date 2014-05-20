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
public class Processor {
    
    public final static int WORD_SIZE = 4;
    public final static byte CH1_INTERRUPT=0b1;
    public final static byte CH2_INTERRUPT=0b10;
    public final static byte CH3_INTERRUPT=0b100;
    public final static byte USER_MODE=0b0;
    public final static byte SUPERUSER_MODE=0b1;
    public final static byte INTERRUPT=0b1;
    public final static byte NO_INTERRUPT=0b0;
    
    byte PLR[] = new byte[WORD_SIZE];
    byte AX[] = new byte[WORD_SIZE];
    byte BX[] = new byte[WORD_SIZE];
    byte IC[] = new byte[2];
    byte C;
    byte MODE;
    byte CH1;
    byte CH2;
    byte CH3;
    byte IOI;
    byte PI;
    byte SI;
    byte TI;
    
    public Processor(byte[] PLR, byte TI) {
        this.PLR = PLR;
        this.TI = TI;
    }


    /**
     * @param MODE the MODE to set
     */
    public void setMODE(byte MODE) {
        this.MODE = MODE;
    }

    /**
     * @param CH1 the CH1 to set
     */
    public void setCH1(byte CH1) {
        this.CH1 = CH1;
    }

    /**
     * @param CH2 the CH2 to set
     */
    public void setCH2(byte CH2) {
        this.CH2 = CH2;
    }

    /**
     * @param CH3 the CH3 to set
     */
    public void setCH3(byte CH3) {
        this.CH3 = CH3;
    }

    /**
     * @param IOI the IOI to set
     */
    public void setIOI(byte IOI) {
        this.IOI = IOI;
    }

    /**
     * @param PI the PI to set
     */
    public void setPI(byte PI) {
        this.PI = PI;
    }

    /**
     * @param SI the SI to set
     */
    public void setSI(byte SI) {
        this.SI = SI;
    }

    /**
     * @param TI the TI to set
     */
    public void setTI(byte TI) {
        this.TI = TI;
    }
    
    public void loadState(Processor other) {
        this.AX = other.AX.clone();
        this.BX = other.BX.clone();
        this.C = other.C;
        this.CH1 = other.CH1;
        this.CH2 = other.CH2;
        this.CH3 = other.CH3;
        this.IC = other.IC.clone();
        this.IOI = other.IOI;
        this.MODE = other.MODE;
        this.PI = other.PI;
        this.PLR = other.PLR.clone();
        this.SI = other.SI;
        this.TI = other.TI;
    }
    
    public void saveState(Processor other) {
        other.AX = this.AX.clone();
        other.BX = this.BX.clone();
        other.C = this.C;
        other.CH1 = this.CH1;
        other.CH2 = this.CH2;
        other.CH3 = this.CH3;
        other.IC = this.IC.clone();
        other.IOI = this.IOI;
        other.MODE = this.MODE;
        other.PI = this.PI;
        other.PLR = this.PLR.clone();
        other.SI = this.SI;
        other.TI = this.TI;
    }
}
