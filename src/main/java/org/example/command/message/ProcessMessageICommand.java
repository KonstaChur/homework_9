package org.example.command.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.command.ICommand;
import org.example.exception.ExceptionHandler;
import org.example.message.CommandMessageDto;

public class ProcessMessageICommand implements ICommand {
    private final String commandMessage;

    private ObjectMapper objectMapper;

    public ProcessMessageICommand(String commandMessage) {
        this.commandMessage = commandMessage;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void execute() {
        try {
            var commandMessageDto = objectMapper.readValue(commandMessage, CommandMessageDto.class);
            new InterpretICommand(commandMessageDto).execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
