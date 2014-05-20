/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package moske;

import moske.ResourceDescriptor.ResourceName;

/**
 *
 * @author LD
 */
public class Resource {
    
    public ResourceDescriptor deskriptorius;
    
    public Resource (int intId, ResourceName extId, OS os, Process creatorProcess, boolean reusable, Object component){
        
        this.deskriptorius = new ResourceDescriptor(intId,extId,os,creatorProcess,reusable, component);
    }
    
    	public ResourceDescriptor getResDesc() {
		return deskriptorius;
	}
        
        	public void setResDesc(ResourceDescriptor deskriptorius) {
		this.deskriptorius = deskriptorius;
	}
}
