/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package os;

/**
 *
 * @author LD
 */
public class ResourceDescriptor {
    
    private int intId;
    private ResourceName extId;
    private Process creatorProcess;
    private boolean reusable;
    private OS os;
    private Object component;
    
    	public enum ResourceName {
		MOS_Pabaiga,
                Vartotojo_atmintis,
                Ivedimo_Irenginys,
                Isvedimo_Irenginys,

		Ivesta_Eilute,
                Eilute_Amintyje,
		Pranesimas_getLine_Procesui,
                Isvesta_Eilute,

		Pertraukimo_Pranesimas,
                Pertraukimas, // processor descr.
                

		Ivestas_Progr_Pav,
                Uzduotis_isroineje,
                Pranesimas_Loader, // is
                Eilute_Supervizor,		
		Uzduotis_Vm, // is Loader (atmintyje)
		Supervizorine_atm
	}
        
        public ResourceDescriptor (int intId, ResourceName extId, OS os, Process creatorProcess, boolean reusable, Object component){
            
            this.intId = intId;
            this.extId = extId;
            this.os = os;
            this.creatorProcess = creatorProcess;
            this.reusable = reusable;
            this.component = component;
        }
        
        	public OS getOS() {
		return os;
	}

	public void setOS(OS os) {
		this.os = os;
	}
	
	public Process getCreatorProcess() {
		return creatorProcess;
	}

	public void setCreatorProcess(Process creatorProcess) {
		this.creatorProcess = creatorProcess;
	}
	public int getIntId() {
		return intId;
	}

	public void setIntId(int intId) {
		this.intId = intId;
	}

	public ResourceName getExtId() {
		return extId;
	}

	public void setExtId(ResourceName extId) {
		this.extId = extId;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public boolean isReusable() {
		return reusable;
	}

	public void setReusable(boolean reusable) {
		this.reusable = reusable;
	}
}
