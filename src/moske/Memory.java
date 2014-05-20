package moske;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LD
 */
import static moske.Utils.*;


public class Memory {
    
    public final static int WORD_SIZE = 4;
    public final static int BLOCK_SIZE = 10;
    public final static int BLOCKS = 70;
    public final static int USER_MEMORY_BLOCKS = 50;
    public final static int PAGE_TABLE_BLOCKS = 10;
    public final byte[] memory;
    

    public Memory(){
    this.memory = new byte[WORD_SIZE * BLOCK_SIZE * BLOCKS];
    }
        
        
        
}
