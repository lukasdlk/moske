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
public abstract class Process implements Comparable<Process> {
    public ProcessDescriptor pDesc;
    public OS os;
    
    public Process (ProcessDescriptor pDesc, OS os) {
        this.pDesc = pDesc;
        this.os = os;
    }
    

    
    public int compareTo(Process o) {
        if (this.pDesc.priority > o.pDesc.priority)
            return 1;
        if (this.pDesc.priority < o.pDesc.priority)
            return -1;
        return 0;
    }
    
}
