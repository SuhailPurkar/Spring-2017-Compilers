// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;
import static jminusminus.NPhysicalRegister.*;
/**
 * Implements register allocation using graph coloring algorithm.
 */

public class NGraphRegisterAllocator extends NRegisterAllocator {
	
	 
	private ArrayList<NInterval> unhandled;
    private ArrayList<NInterval> active;
    private ArrayList<NInterval> inactive;
	
	//HW6 Added Algorithm/Pseusdocode
  	//registerAssignedSucessfully -> false
  	//repeat
  	//	repeat
  	//		buildIntervals()
  	//		buildInterferenceGraph()
  	//	until coalesceRegistersSucessful()
  	//	buildadjLists()
  	//	computeSpillCosts()
  	//	pruneGraph()
  	//	registerAssignedSucessfully <- assignRegisterSucessful()
  	//	if registerAssignedfully then
  	//		generateSpillCode()
  	//	end if
  	//until registerAssignedSucessfully
	
	//HW6 Added
	 
	
    /**
     * Construct a NGraphRegisterAllocator.
     * 
     * @param cfg
     *            an instance of a control flow graph.
     */

    public NGraphRegisterAllocator(NControlFlowGraph cfg) {
        super(cfg);
        //HW6 Added
          
		 unhandled = new ArrayList<NInterval>();
		 active = new ArrayList<NInterval>();
         inactive = new ArrayList<NInterval>();
         Boolean [][] adjLists = new Boolean[6][6];
		
		 
		 
        }   
    

	
	
	/**
     * Adds a given interval onto the unhandled list, maintaining an order based
     * on the first range start of the NIntervals.
     * 
     * @param newInterval
     *            the NInterval to sort onto unhandled.
     */

    private void addSortedToUnhandled(NInterval newInterval) {
        if (unhandled.isEmpty()) {
            unhandled.add(newInterval);
        } else {
			
            unhandled.add(0, newInterval);
        }	
    }
	

	
	NInterval currInterval; // the current interval
	int psi; // the current interval's first start position
	ArrayList<NInterval> tmp; 
	
	public void buildInterferenceGraph(){
		
		for(int i = 0; i < active.size(); i++){
			for(int j = 0; j < active.size(); j++){ 
				for(int k = 0; k < active.get(i).ranges.size(); k++){ 
					for(int l = 0; l < active.get(j).ranges.size();l++){ 
						
						buildIntervals();
						
						currInterval = unhandled.remove(0);
						psi = currInterval.firstRangeStart();
						tmp = new ArrayList<NInterval>();
						//borrowed from LinearRegisterAllocator
						if (active.get(i).lastNRangeStop() < psi) {
						tmp.add(active.get(i));
						} else if (!active.get(i).isLiveAt(psi)) {
						inactive.add(active.get(i));
						tmp.add(active.get(i));
						}
							
						
						
					}
					
				}
			}
		}
    }
    
    
    public void buildAdjacencyLists(){
		for(int i = 0; i < active.size(); i++){
			for(int j = 0; j < active.size(); j++){
			//build a list of vectors from computed vectors from 
			//adjacencyMatrix.
			Boolean [][] adjLists = new Boolean[i][j];
			
			}
		}
    }
    
  
    
    public void computeSpillCosts(){
    	
		//TO DO
    	
	
    }
    
    public void pruneGraph(){
    	
    	//TO DO
    	
    	
    }
    
    public void generateSpillCode() {
    
		//TO DO
 	
    }
    
    public boolean coalesceRegister() {
		//TO DO
    	return false;
    }
	
	
	
    /**
     * Build intervals with register allocation information in them.
     */
     
    
    public void allocation() {
        
		// Build the intervals for the control flow graph.
        this.buildIntervals(); // The correct intervals are now in intervals
		
        //from NaiveRegisterAllocator
		// Allocate any fixed registers (a0, ..., a3 and v0) that were
        // assigned during generation phase to the appropriate
        // interval.
        for (int i = 0; i < 32; i++) {
            if (cfg.registers.get(i) != null) {
                cfg.intervals.get(i).pRegister = (NPhysicalRegister) cfg.registers
                        .get(i);
            }
        }
		
		//Borrowed from NLinearRegisterAllocator
		// Add all intervals corresponding to vregs to unhandled list
        for (int i = 32; i < cfg.intervals.size(); i++) {
            this.addSortedToUnhandled(cfg.intervals.get(i));
        }
		
		
		
		buildInterferenceGraph();
		
		//Also from NNaiveRegisterAllocator
		// Assign stack offset (relative to fp) for formal parameters
        // fourth and above, and stack offset (relative to sp) for
        // arguments fourth or above.
        for (NBasicBlock block : cfg.basicBlocks) {
            for (NLIRInstruction lir : block.lir) {
                if (lir instanceof NLIRLoadLocal) {
                    NLIRLoadLocal loadLocal = (NLIRLoadLocal) lir;
                    if (loadLocal.local >= 4) {
                        NInterval interval = cfg.intervals
                                .get(((NVirtualRegister) loadLocal.write)
                                        .number());
                        interval.spill = true;
                        interval.offset = loadLocal.local - 3;
                        interval.offsetFrom = OffsetFrom.FP;
                    }
                }
            }
        }
		

	
		buildAdjacencyLists();
        computeSpillCosts();
        pruneGraph();
	
	}
	
	}