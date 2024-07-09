package org.example.command.log;


import lombok.extern.slf4j.Slf4j;
import org.example.command.ICommand;
import org.example.queue.LinkedListCommandQueue;

@Slf4j
public class LogQueueSizeICommand implements ICommand {

    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        log.info("Queue size: {}", queue.size());
    }
}
