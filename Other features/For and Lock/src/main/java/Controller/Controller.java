package Controller;

import Model.ADTStack.MyIStack;
import Exception.MyException;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Value.ReferenceValue;
import Model.Value.Value;
import Repository.IRepository;
import Repository.Repository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    IRepository repository;
    int displayFlag;
    ExecutorService executor;
    public Controller(IRepository repository) {
        this.repository = repository;
        displayFlag = 0;
    }

    /*public IRepository getRepository() {
        return repository;
    }

    public int getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(int displayFlag) {
        this.displayFlag = displayFlag;
    }*/

    public void allSteps() throws MyException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //System.out.println(repository.getProgramStates().size());
        List<ProgramState> programStates = removeCompletedPrograms(repository.getProgramStates());

        /*programStates.forEach(programState -> {
            try {
                repository.logPrgStateExec(programState);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });*/

        //System.out.println(programStates.size());
        while(programStates.size()>0){
            oneStepForAllPrograms(programStates);
            programStates.forEach(program -> program.getHeap().setHeap((HashMap<Integer, Value>) safeGarbageCollector(getAddressesFromSymAndHeap(program.getSymTable().getDictionary().values(), program.getHeap().getHeap().values()), program.getHeap().getHeap())));

            programStates = removeCompletedPrograms(repository.getProgramStates());
        }

        executor.shutdownNow();
        repository.setProgramStates(programStates);
    }


    /*String displayCurrentProgramState(){
        if(displayFlag == 1){
            return repository.getCrtPrg().toString();
        }
        return null;
    }*/

    /*public void setCurrentProgram(int index){
        repository.setCurrentProgram(index);
    }*/



   /* void addPrgState(ProgramState state){
        repository.addPrgState(state);
    }*/

    Map<Integer, Value> safeGarbageCollector(List<Integer> symTable, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

   /* List<Integer> getAddressFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v->v instanceof ReferenceValue)
                .map(v->{ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }*/

    List<Integer> getAddressesFromSymAndHeap(Collection<Value> symTableValues, Collection<Value> heap) {
        return Stream.concat(
                        heap.stream()
                                .filter(v -> v instanceof ReferenceValue)
                                .map(v -> { ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress();}),
                        symTableValues.stream()
                                .filter(v -> v instanceof ReferenceValue)
                                .map(v -> { ReferenceValue v1 = (ReferenceValue) v; return v1.getAddress(); })
                )
                .collect(Collectors.toList());
    }

    List<ProgramState> removeCompletedPrograms(List<ProgramState> programStates){
        return programStates.stream()
                .filter(ProgramState::isNotCompleted)
                .collect(Collectors.toList());
    }

    void oneStepForAllPrograms(List<ProgramState> programStates) throws InterruptedException {
        /*programStates.forEach(programState -> {
            try {
                repository.logPrgStateExec(programState);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });*/
        /*System.out.println("prg states: ");
        System.out.println(repository.getProgramStates().size());*/

        List<Callable<ProgramState>>callList = programStates.stream()
                .map((ProgramState program) -> (Callable<ProgramState>) (program::oneStep)).toList();

        List <ProgramState> newProgramStatesList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e.toString());
                    }
                })
                .filter(Objects::nonNull).toList();
        programStates.addAll(newProgramStatesList);

        programStates.forEach(programState -> {
            try {
                repository.logPrgStateExec(programState);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });

        repository.setProgramStates(programStates);
    }


    /*public Controller copy() {
        Repository repo_copy = new Repository(this.repository.getProgramStates(), this.repository.getPath());
        return new Controller(repo_copy);
    }*/

    public void oneStepGUI() throws InterruptedException{
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programStates = removeCompletedPrograms(repository.getProgramStates());
        programStates.forEach(p -> {
            try {
                repository.logPrgStateExec(p);
            } catch (MyException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        if (programStates.size() > 0) {
            programStates.get(0).getHeap().setHeap((HashMap<Integer, Value>) safeGarbageCollector(getAddressesFromSymAndHeap(programStates.get(0).getSymTable().getDictionary().values(), programStates.get(0).getHeap().getHeap().values()), programStates.get(0).getHeap().getHeap()));
            oneStepForAllPrograms(programStates);
//            programStates = removeCompletedPrograms(repo.getPrograms());
        } else {
            executor.shutdownNow();
            repository.setProgramStates(programStates);
            throw new RuntimeException("Program Finished!");
        }
        executor.shutdownNow();
        repository.setProgramStates(programStates);
    }
}

