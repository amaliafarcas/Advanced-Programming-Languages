package Repository;

import Exception.MyException;
import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Repository implements IRepository {
    List<ProgramState> programStates;
    //int currentProgram;

    String logFilePath;

    public Repository(List<ProgramState> programStates, String logFilePath) {
        this.programStates = programStates;
        //this.currentProgram = 0;
        this.logFilePath = logFilePath;
    }

    @Override
    public List<ProgramState> getProgramStates() {
        return programStates;
    }

    public void setProgramStates(List<ProgramState> programStates) {
        this.programStates = programStates;
    }

    /*public int getCurrentProgram() {
        return currentProgram;
    }

    public void setCurrentProgram(int currentProgram) {
        this.currentProgram = currentProgram;
    }
*/
    @Override
    public void addPrgState(ProgramState state) {
        programStates.add(state);
    }

    /*@Override
    public ProgramState getCrtPrg() {
        return programStates.get(currentProgram);
    }*/


    @Override
    public void logPrgStateExec(ProgramState programState) throws MyException, IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        pw.write(programState.toString());
        //System.out.println("saved ");
        pw.close();
    }

    @Override
    public String getPath() {
        return logFilePath;
    }
}
