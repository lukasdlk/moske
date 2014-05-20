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
import static os.Utils.*;


public class VirtualMachine extends Process {
    public Processor processor;
    public Memory memory;
    public char X;
    public char Y;

    public VirtualMachine(ProcessDescriptor pDesc , OS os){
        super(pDesc, os);
        this.pDesc.extID = "Virtual_Machine";
        this.pDesc.intID = ProcessDescriptor.ProcessesName.VirtualMachine;
        this.processor = os.processor;
    }
    
        public void resetC() {
        processor.C = (byte) (processor.C ^ processor.C);
    }

    public void setSF(boolean val) {
        processor.C = (byte) (val ? (processor.C | 0b00000100) : (processor.C ^ 0b00000100));
    }

    public void setOF(boolean val) {
        processor.C = (byte) (val ? (processor.C | 0b00000010) : (processor.C ^ 0b00000010));
    }

    public void setZF(boolean val) {
        processor.C = (byte) (val ? (processor.C | 0b00000001) : (processor.C ^ 0b00000001));
    }

    public boolean getSF() {
        return ((processor.C & 0b00000100) > 0) ? true : false;
    }

    public boolean getOF() {
        return ((processor.C & 0b00000100) > 0) ? true : false;
    }

    public boolean getZF() {
        return ((processor.C & 0b00000100) > 0) ? true : false;
    }

    public void incIC() throws CastException {
        int x = byteToInt(processor.IC[0]);
        int y = byteToInt(processor.IC[1]);
        int IC_int = x * 10 + y;
        IC_int++;
        processor.IC[0] = intToByte(IC_int / 10);
        processor.IC[1] = intToByte(IC_int % 10);
    }

    public void commandLA(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            for (int i = 0; i < 4; i++) {
                processor.AX[i] = memory.memory[address + i];
            }
            int AX_int = wordToInt(processor.AX, 0);
            if (AX_int == 0) {
                setZF(true);
            } else if (AX_int < 0) {
                setSF(true);
            }
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandLB(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            for (int i = 0; i < 4; i++) {
                processor.BX[i] = memory.memory[address + i];
            }
            int BX_int = wordToInt(processor.BX, 0);
            if (BX_int == 0) {
                setZF(true);
            } else if (BX_int < 0) {
                setSF(true);
            }
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandLAfB() throws CastException {
        resetC();
        incIC();
        if (byteToInt(processor.BX[0]) + byteToInt(processor.BX[1]) > 0) {
            processor.PI = intToByte(1);
            return;
        }
        try {
            int address = realAddress(byteToChar(processor.BX[2]), byteToChar(processor.BX[3]));
            for (int i = 0; i < 4; i++) {
                processor.AX[i] = memory.memory[address + i];
            }
            int AX_int = wordToInt(processor.AX, 0);
            if (AX_int == 0) {
                setZF(true);
            } else if (AX_int < 0) {
                setSF(true);
            }
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandLBfA() throws CastException {
        resetC();
        incIC();
        if (processor.AX[0] + processor.AX[1] > 0) {
            processor.PI = intToByte(1);
            return;
        }
        try {
            int address = realAddress(byteToChar(processor.AX[2]), byteToChar(processor.AX[3]));
            for (int i = 0; i < 4; i++) {
                processor.BX[i] = memory.memory[address + i];
            }
            int BX_int = wordToInt(processor.AX, 0);
            if (BX_int == 0) {
                setZF(true);
            } else if (BX_int < 0) {
                setSF(true);
            }
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandSA(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            for (int i = 0; i < 4; i++) {
                memory.memory[address + i] = processor.AX[i];
            }
            int AX_int = wordToInt(processor.AX, 0);
            if (AX_int == 0) {
                setZF(true);
            } else if (AX_int < 0) {
                setSF(true);
            }
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandSB(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            for (int i = 0; i < 4; i++) {
                memory.memory[address + i] = processor.BX[i];
            }
            int BX_int = wordToInt(processor.BX, 0);
            if (BX_int == 0) {
                setZF(true);
            } else if (BX_int < 0) {
                setSF(true);
            }
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandCOPA() throws CastException {
        resetC();
        incIC();
        for (int i = 0; i < 4; i++) {
            processor.AX[i] = processor.BX[i];
        }
        int AX_int = wordToInt(processor.AX, 0);
        if (AX_int == 0) {
            setZF(true);
        } else if (AX_int < 0) {
            setSF(true);
        }
    }

    public void commandCOPB() throws CastException {
        resetC();
        incIC();
        for (int i = 0; i < 4; i++) {
            processor.BX[i] = processor.AX[i];
        }
        int BX_int = wordToInt(processor.AX, 0);
        if (BX_int == 0) {
            setZF(true);
        } else if (BX_int < 0) {
            setSF(true);
        }
    }

    public void commandAW(char x) throws CastException {
        resetC();
        incIC();
        for (int i = 0; i < 3; i++) {
            processor.BX[i] = intToByte(0);
        }
        processor.BX[3] = charToByte(x);
        int BX_int = wordToInt(processor.AX, 0);
        if (BX_int == 0) {
            setZF(true);
        } else if (BX_int < 0) {
            setSF(true);
        }
    }

    public void commandAA(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            int AX_int = wordToInt(processor.AX, 0), memory_int = wordToInt(memory.memory, address);
            try {
                AX_int = addWithOverflow(AX_int, memory_int);
            } catch (OverflowException e) {
                setOF(true);
                processor.PI = intToByte(4);
            }
            if (AX_int == 0) {
                setZF(true);
            } else if (AX_int < 0) {
                setSF(true);
            }
            intToWord(AX_int, processor.AX, 0);
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandAB(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            int BX_int = wordToInt(processor.BX, 0), memory_int = wordToInt(memory.memory, address);
            try {
                BX_int = addWithOverflow(BX_int, memory_int);
            } catch (OverflowException e) {
                setOF(true);
                processor.PI = intToByte(4);
            }
            if (BX_int == 0) {
                setZF(true);
            } else if (BX_int < 0) {
                setSF(true);
            }
            intToWord(BX_int, processor.BX, 0);
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandBA(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            int AX_int = wordToInt(processor.AX, 0), memory_int = wordToInt(memory.memory, address);
            try {
                AX_int = subWithOverflow(AX_int, memory_int);
            } catch (OverflowException e) {
                setOF(true);
                processor.PI = intToByte(4);
            }
            if (AX_int == 0) {
                setZF(true);
            } else if (AX_int < 0) {
                setSF(true);
            }
            intToWord(AX_int, processor.AX, 0);
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandBB(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            int BX_int = wordToInt(processor.BX, 0), memory_int = wordToInt(memory.memory, address);
            try {
                BX_int = subWithOverflow(BX_int, memory_int);
            } catch (OverflowException e) {
                setOF(true);
                processor.PI = intToByte(4);
            }
            if (BX_int == 0) {
                setZF(true);
            } else if (BX_int < 0) {
                setSF(true);
            }
            intToWord(BX_int, processor.BX, 0);
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandMA(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            int AX_int = wordToInt(processor.AX, 0), memory_int = wordToInt(memory.memory, address);
            try {
                AX_int = mulWithOverflow(AX_int, memory_int);
            } catch (OverflowException e) {
                setOF(true);
                processor.PI = intToByte(4);
            }
            if (AX_int == 0) {
                setZF(true);
            } else if (AX_int < 0) {
                setSF(true);
            }
            intToWord(AX_int, processor.AX, 0);
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandMB(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            int BX_int = wordToInt(processor.BX, 0), memory_int = wordToInt(memory.memory, address);
            try {
                BX_int = mulWithOverflow(BX_int, memory_int);
            } catch (OverflowException e) {
                setOF(true);
                processor.PI = intToByte(4);
            }
            if (BX_int == 0) {
                setZF(true);
            } else if (BX_int < 0) {
                setSF(true);
            }
            intToWord(BX_int, processor.BX, 0);
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandDA(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            int AX_int = wordToInt(processor.BX, 0), BX_int, memory_int = wordToInt(memory.memory, address);
            if (memory_int == 0) {
                processor.PI = intToByte(3);
                return;
            }
            BX_int = AX_int % memory_int;
            AX_int /= memory_int;
            if (AX_int == 0) {
                setZF(true);
            } else if (AX_int < 0) {
                setSF(true);
            }
            intToWord(AX_int, processor.AX, 0);
            intToWord(BX_int, processor.BX, 0);
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandDECA() throws CastException {
        resetC();
        incIC();
        int AX_int = wordToInt(processor.AX, 0);
        try {
            AX_int = subWithOverflow(AX_int, 1);
        } catch (OverflowException e) {
            setOF(true);
        }
        if (AX_int == 0) {
            setZF(true);
        } else if (AX_int < 0) {
            setSF(true);
        }
        intToWord(AX_int, processor.AX, 0);
    }

    public void commandDECB() throws CastException {
        resetC();
        incIC();
        int BX_int = wordToInt(processor.BX, 0);
        try {
            BX_int = subWithOverflow(BX_int, 1);
        } catch (OverflowException e) {
            setOF(true);
        }
        if (BX_int == 0) {
            setZF(true);
        } else if (BX_int < 0) {
            setSF(true);
        }
        intToWord(BX_int, processor.BX, 0);
    }

    public void commandINCA() throws CastException {
        resetC();
        incIC();
        int AX_int = wordToInt(processor.AX, 0);
        try {
            AX_int = addWithOverflow(AX_int, 1);
        } catch (OverflowException e) {
            setOF(true);
        }
        if (AX_int == 0) {
            setZF(true);
        } else if (AX_int < 0) {
            setSF(true);
        }
        intToWord(AX_int, processor.AX, 0);
    }

    public void commandINCB() throws CastException {
        resetC();
        incIC();
        int BX_int = wordToInt(processor.BX, 0);
        try {
            BX_int = addWithOverflow(BX_int, 1);
        } catch (OverflowException e) {
            setOF(true);
        }
        if (BX_int == 0) {
            setZF(true);
        } else if (BX_int < 0) {
            setSF(true);
        }
        intToWord(BX_int, processor.BX, 0);
    }

    public void commandCA(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            int AX_int = wordToInt(processor.AX, 0), memory_int = wordToInt(memory.memory, address);
            try {
                AX_int = subWithOverflow(AX_int, memory_int);
            } catch (OverflowException e) {
                setOF(true);
                processor.PI = intToByte(4);
            }
            if (AX_int == 0) {
                setZF(true);
            } else if (AX_int < 0) {
                setSF(true);
            }
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandCB(char x, char y) throws CastException {
        resetC();
        incIC();
        try {
            int address = realAddress(x, y);
            int BX_int = wordToInt(processor.BX, 0), memory_int = wordToInt(memory.memory, address);
            try {
                BX_int = subWithOverflow(BX_int, memory_int);
            } catch (OverflowException e) {
                setOF(true);
                processor.PI = intToByte(4);
            }
            if (BX_int == 0) {
                setZF(true);
            } else if (BX_int < 0) {
                setSF(true);
            }
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandIP(char x, char y) throws CastException {
        try {
            int address = realAddress(x, y);
            X = x;
            Y = y;
            processor.processor.SI = intToByte(1);
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandOP(char x, char y) throws CastException {
        try {
            int address = realAddress(x, y);
            X = x;
            Y = y;
            processor.SI = intToByte(2);
        } catch (ClassCastException e) {
            processor.PI = intToByte(1);
        }
    }

    public void commandJP(char x, char y) throws CastException {
        processor.IC[0] = charToByte(x);
        processor.IC[1] = charToByte(y);
    }

    public void commandJE(char x, char y) throws CastException {
        incIC();
        if (getZF()) {
            commandJP(x, y);
        }
    }

    public void commandJL(char x, char y) throws CastException {
        incIC();
        if (!getZF() && (getSF() == getOF())) {
            commandJP(x, y);
        }
    }

    public void commandJG(char x, char y) throws CastException {
        incIC();
        if (!getZF() && (getSF() != getOF())) {
            commandJP(x, y);
        }
    }

    public void commandHALT() throws CastException {
        incIC();
        processor.SI = intToByte(3);
    }

    public void commandGEC(char x) throws CastException {
        incIC();
        if (x < '1' || '3' < x) {
            processor.PI = intToByte(1);
            return;
        }
        intToWord(0, processor.BX, 0);
        if (x == '1' && getSF()) {
            processor.BX[3] = intToByte(1);
        }
        if (x == '2' && getOF()) {
            processor.BX[3] = intToByte(1);
        }
        if (x == '3' && getZF()) {
            processor.BX[3] = intToByte(1);
        }
    }

    public void commandSEC(char x) throws CastException {
        incIC();
        if (x < '1' || '3' < x) {
            processor.PI = intToByte(1);
            return;
        }
        boolean val = false;
        int BX_int = wordToInt(processor.BX, 0);
        if (BX_int == 1) {
            val = true;
        }
        if (x == '1') {
            setSF(val);
        }
        if (x == '2' && getOF()) {
            setOF(val);
        }
        if (x == '3') {
            setZF(val);
        }
    }

    public void commandGEIC() throws CastException {
        intToWord(0, processor.BX, 0);
        processor.BX[2] = processor.IC[0];
        processor.BX[3] = processor.IC[1];
        incIC();
    }

    public void commandSEIC() {
        processor.IC[0] = processor.BX[2];
        processor.IC[1] = processor.BX[3];
    }

    public void StartIO() throws CastException, BufferOverflowException {
        if (channelNumber == 1) {
            channelNumber = 0;
            processor.CH1 = intToByte(1);
            String input = console.readLine("Plz enter somthing WOW: ");
            int len = input.length();
            if (len > 40) {
                len = 40;
            }
            for (int i = 0; i < len; i++) {
                try {
                    channelDeviceBuffer[i] = charToByte(input.charAt(i));
                } catch (ClassCastException e) {
                    channelDeviceBuffer[i] = charToByte('?');
                }
            }
            if (len < 40) {
                channelDeviceBuffer[len] = charToByte('#');
            }
            int startPoz = charToInt(X) * 10 + charToInt(Y);
            outerloopCH1:
            for (int i = 0; i < BLOCK_SIZE; i++) {
                try {
                    char x = intToChar((startPoz + i) / 10);
                    char y = intToChar((startPoz + i) % 10);
                    int address = realAddress(x, y);
                    for (int j = 0; j < 4; j++) {
                        if (channelDeviceBuffer[i * WORD_SIZE + j] == '#') {
                            break outerloopCH1;
                        }
                        memory.memory[address + j] = channelDeviceBuffer[i * WORD_SIZE + j];
                    }
                } catch (ClassCastException e) {
                    throw new BufferOverflowException("Do not write a poem.");
                }
            }
            processor.CH1 = intToByte(0);
            processor.IOI = intToByte(byteToInt(processor.IOI) + 1);
        }
        if (channelNumber == 2) {
            channelNumber = 0;
            processor.CH2 = intToByte(1);
            int startPoz = charToInt(X) * 10 + charToInt(Y);
            outerloopCH2:
            for (int i = 0; i < 10; i++) {
                try {
                    char x = intToChar((startPoz + i) / 10);
                    char y = intToChar((startPoz + i) % 10);
                    int address = realAddress(x, y);
                    for (int j = 0; j < 4; j++) {
                        channelDeviceBuffer[i * WORD_SIZE + j] = memory.memory[address + j];
                        if (memory.memory[address + j] == '#') {
                            break outerloopCH2;
                        }
                    }
                } catch (ClassCastException e) {
                    throw new BufferOverflowException("Do not write a poem.");
                }
            }
            processor.CH2 = intToByte(0);
            processor.IOI = intToByte(byteToInt(processor.IOI) + 2);
        }
        if (channelNumber == 3) {
            channelNumber = 0;
            processor.CH3 = intToByte(1);
            processor.CH3 = intToByte(0);
            processor.IOI = intToByte(byteToInt(processor.IOI) + 4);
        }
    }

    public void intepreteNextCommand() throws CastException {
        try {
            char x = byteToChar(processor.IC[0]);
            char y = byteToChar(processor.IC[1]);
            int address = realAddress(x, y);
            String command = "";
            for (int i = 0; i < 4; i++) {
                command += byteToChar(memory.memory[address + i]);
            }
            System.out.println(command);
            String commandStart = command.substring(0, 2);
            x = command.charAt(2);
            y = command.charAt(3);
            switch (commandStart) {
                case "LA":
                    if (x == 'f' && y == 'B') {
                        commandLAfB();
                    } else {
                        commandLA(x, y);
                    }
                    break;
                case "LB":
                    if (x == 'f' && y == 'A') {
                        commandLBfA();
                    } else {
                        commandLB(x, y);
                    }
                    break;
                case "SA":
                    commandSA(x, y);
                    break;
                case "SB":
                    commandSB(x, y);
                    break;
                case "CO":
                    if (x == 'P' && y == 'A') {
                        commandCOPA();
                    } else if (x == 'P' && y == 'B') {
                        commandCOPB();
                    } else {
                        throw new MachineException(command);
                    }
                    break;
                case "AW":
                    commandAW(x);
                    break;
                case "AA":
                    commandAA(x, y);
                    break;
                case "AB":
                    commandAB(x, y);
                    break;
                case "BA":
                    commandBA(x, y);
                    break;
                case "BB":
                    commandBB(x, y);
                    break;
                case "MA":
                    commandMA(x, y);
                    break;
                case "MB":
                    commandMB(x, y);
                    break;
                case "DA":
                    commandDA(x, y);
                    break;
                case "DE":
                    if (x == 'C' && y == 'A') {
                        commandDECA();
                    } else if (x == 'C' && y == 'B') {
                        commandDECB();
                    } else {
                        throw new MachineException(command);
                    }
                    break;
                case "IN":
                    if (x == 'C' && y == 'A') {
                        commandINCA();
                    } else if (x == 'C' && y == 'B') {
                        commandINCB();
                    } else {
                        throw new MachineException(command);
                    }
                    break;
                case "CA":
                    commandCA(x, y);
                    break;
                case "CB":
                    commandCB(x, y);
                    break;
                case "IP":
                    commandIP(x, y);
                    break;
                case "OP":
                    commandOP(x, y);
                    break;
                case "JP":
                    commandJP(x, y);
                    break;
                case "JE":
                    commandJE(x, y);
                    break;
                case "JL":
                    commandJL(x, y);
                    break;
                case "JG":
                    commandJG(x, y);
                    break;
                case "HA":
                    if (x == 'L' && y == 'T') {
                        commandHALT();
                    } else {
                        throw new MachineException(command);
                    }
                    break;
                case "GE":
                    if (x == 'C') {
                        commandGEC(y);
                    } else if (x == 'I' && y == 'C') {
                        commandGEIC();
                    } else {
                        throw new MachineException(command);
                    }
                    break;
                case "SE":
                    if (x == 'C') {
                        commandSEC(y);
                    } else if (x == 'I' && y == 'C') {
                        commandSEIC();
                    } else {
                        throw new MachineException(command);
                    }
                    break;
                default:
                    incIC();
                    throw new MachineException(command);
            }
        } catch (MachineException e) {
            System.err.println(e.getStackTrace());
            processor.PI = intToByte(1);
        }
    }

    public void checkInterrupt() throws CastException {

        if (byteToInt(processor.TI) == 0) {
            System.out.println("Program has exceeded its time limit");
            processor.MODE = 1;
            restartTimer();
            processor.MODE = 0;
        }

        if (byteToInt(processor.PI) != 0) {
            switch (byteToInt(processor.PI)) {
                case 1:
                    System.out.println("PROGRAM INTERRUPT! Incorrect command");
                    processor.MODE = 1;
                    stopProgram();
                    break;
                case 2:
                    System.out.println("PROGRAM INTERRUPT! Negative result");
                    processor.MODE = 1;
                    stopProgram();
                    break;
                case 3:
                    System.out.println("PROGRAM INTERRUPT! Division by zero");
                    processor.MODE = 1;
                    stopProgram();
                    break;
                case 4:
                    System.out.println("PROGRAM INTERRUPT! Program overflow!");
                    processor.MODE = 1;
                    stopProgram();
                    break;
            }
        }

        if (byteToInt(processor.SI) != 0) {
            switch (byteToInt(processor.SI)) {
                case 1:
                    System.out.println("PROGRAM INTERRUPT! Data input!");
                    processor.MODE = 1;
                    channelNumber = 1;
                    processor.MODE = 0;
                    processor.SI = 0;
                    break;
                case 2:
                    System.out.println("PROGRAM INTERRUPT! Data output!");
                    processor.MODE = 1;
                    channelNumber = 2;
                    processor.MODE = 0;
                    processor.SI = 0;
                    break;
                case 3:
                    System.out.println("PROGRAM INTERRUPT! Command halt!");
                    processor.MODE = 1;
                    stopProgram();
                    break;
            }
        }
    }

    
}
