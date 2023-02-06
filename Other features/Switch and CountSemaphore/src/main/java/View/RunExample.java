package View;

import Controller.Controller;
import Exception.MyException;
import Model.Type.Type;

import java.io.IOException;

public class RunExample extends Command{

    private Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try{
            controller.allSteps();
        }catch (MyException | IOException exception) {
            System.out.println(exception.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
