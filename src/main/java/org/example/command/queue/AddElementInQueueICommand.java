package org.example.command.queue;

import org.example.command.ICommand;
import org.example.queue.LinkedListCommandQueue;

public class AddElementInQueueICommand implements ICommand {
    private final LinkedListCommandQueue queue;
    private final ICommand element;

    public AddElementInQueueICommand(ICommand element) {
        this.queue = LinkedListCommandQueue.getInstance();
        this.element = element;
    }

    public AddElementInQueueICommand(
            LinkedListCommandQueue queue,
            ICommand element
    ) {
        this.queue = queue;
        this.element = element;
    }

    @Override
    public void execute() {
        queue.push(element);
    }
}
