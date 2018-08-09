package com.epam.market.factory;
import com.epam.market.command.ActionCommand;
import com.epam.market.command.EmptyCommand;
import com.epam.market.command.MapOfCommand;
import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static  final String PARAM_NAME_COMMAND = "command";
    private static  final String ATTRIBUTE_NAME_ERROR = "wrongAction";
    public ActionCommand defineCommand(HttpServletRequest request){
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(PARAM_NAME_COMMAND);
        if(action == null || action.isEmpty()){
            return current;
        }
        try{
        MapOfCommand.getMapPath();
         current = MapOfCommand.getMapPath().get(action.toUpperCase());
        }catch (IllegalArgumentException e){
            request.setAttribute(ATTRIBUTE_NAME_ERROR, action );
        }
        return  current;
    }
}
