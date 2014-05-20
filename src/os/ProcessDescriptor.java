/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package os;

import java.util.LinkedList;

/**
 *
 * @author adomas
 */
public class ProcessDescriptor {
    public enum ProcessesName {
        StartStop,
        WaitForJob,
        MainProc,
        Loader,
        JobGovernor,
        VirtualMachine,
        Interrupt,
        GetLine,
        PrintLine
    }
    public enum ProcessesState {
        RUNNING,
        READY,
        BLOCKED,
        SUSPENDED_READY,
        SUSPENDED_BLOCKED
    }
    
    public ProcessesName intID;
    public String extID;
    public Processor savedState;
    public LinkedList<Resource> createdRes;
    public LinkedList<Resource> ownedRes;
    public Process parentProc;
    public LinkedList<Process> childrenProc;
    public ProcessesState pState;
    public short priority;
    
    public ProcessDescriptor (ProcessesName intID, String extID, Process parentProc, short priority) {
        this.intID = intID;
        this.extID = extID;
        this.parentProc = parentProc;
        this.priority = priority;
        this.savedState = null;
        this.createdRes = new LinkedList<>();
        this.ownedRes = new LinkedList<>();
        this.childrenProc = new LinkedList<>();
        this.pState = ProcessesState.READY;
    }
}
