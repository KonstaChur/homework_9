package org.example.command.queue;


import org.example.command.ICommand;
import org.example.queue.LinkedListCommandQueue;

import java.util.List;

public class AddElementsListInQueueICommand implements ICommand {
    private final LinkedListCommandQueue queue;
    private final List<ICommand> elements;

    public AddElementsListInQueueICommand(List<ICommand> elements) {
        this.queue = LinkedListCommandQueue.getInstance();
        this.elements = elements;
    }

    public AddElementsListInQueueICommand(
            LinkedListCommandQueue queue,
            List<ICommand> elements
    ) {
        this.queue = queue;
        this.elements = elements;
    }

    @Override
    public void execute() {
        elements
                .forEach(queue::push);
    }
}
