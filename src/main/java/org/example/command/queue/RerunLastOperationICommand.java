package org.example.command.queue;

import org.example.command.ICommand;
import org.example.queue.LinkedListCommandQueue;

public class RerunLastOperationICommand implements ICommand {
    @Override
    public void execute() {
        var queue = LinkedListCommandQueue.getInstance();
        queue.peek().execute();
    }
}
