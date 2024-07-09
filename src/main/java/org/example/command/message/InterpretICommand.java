package org.example.command.message;

import org.example.command.ICommand;
import org.example.command.log.LogErrorICommand;
import org.example.command.operations.move.MoveLinearICommand;
import org.example.command.operations.move.MoveNonLinearICommand;
import org.example.command.queue.AddElementInQueueICommand;
import org.example.exception.exceptions.CommandException;
import org.example.game_objects.UObject;
import org.example.game_objects.UObjectWithId;
import org.example.game_objects.spaceship.DescribableByIdSpaceshipsObject;
import org.example.message.CommandMessageDto;
import org.example.queue.Games;

public class InterpretICommand implements ICommand {
    private final UObjectWithId objects;
    private final CommandMessageDto commandMessageDto;

    public InterpretICommand(CommandMessageDto commandMessageDto) {
        this.objects = DescribableByIdSpaceshipsObject.getInstance();
        this.commandMessageDto = commandMessageDto;
    }

    @Override
    public void execute() {
        var games = Games.getInstance();
        var gameQueue = games.getGameQueueById(commandMessageDto.getGameId());
        var object = (UObject) objects.getProperty(commandMessageDto.getObjId());

        var operationId = commandMessageDto.getOperationId();
        var command = getCommandForObjectByOperationIdAndArgs(object, operationId);

        new AddElementInQueueICommand(gameQueue, command).execute();
    }

    private ICommand getCommandForObjectByOperationIdAndArgs(
            UObject object,
            String operationId
    ) {
        ICommand ICommand;
        switch (operationId) {
            case "MoveLinearCommand":
                ICommand = new MoveLinearICommand(object);
                break;
            case "MoveNonLinearCommand":
                ICommand = new MoveNonLinearICommand(object);
                break;
            default:
                ICommand = new LogErrorICommand(this, new CommandException("Unknown operationId: "+operationId));
                break;
        }
        return ICommand;
    }
}
