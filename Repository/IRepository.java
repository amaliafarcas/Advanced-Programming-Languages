package Repository;
import Exception.MyException;
import Model.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    void addPrgState(ProgramState state);

    //ProgramState getCrtPrg();

    List<ProgramState> getProgramStates();

    //void setCurrentProgram(int index);
    void setProgramStates(List<ProgramState> programStates);

    void logPrgStateExec(ProgramState programState) throws MyException, IOException;

    public String getPath();

}
