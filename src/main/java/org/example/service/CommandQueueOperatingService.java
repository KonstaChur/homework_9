package org.example.service;

import org.example.command.ICommand;
import org.example.command.queue.DeleteFirstElemenInQueueICommand;
import org.example.exception.ExceptionHandler;
import org.example.queue.LinkedListCommandQueue;

public class CommandQueueOperatingService {
    private final LinkedListCommandQueue queue;

    public CommandQueueOperatingService() {
        this.queue = LinkedListCommandQueue.getInstance();
    }

    public void process() {
        while (queue.size() > 0) {
            ICommand cmd = queue.peek();
            try {
                cmd.execute();
            } catch (Exception e) {
                ExceptionHandler
                        .handle(e, cmd)
                        .execute();
            } finally {
                 new DeleteFirstElemenInQueueICommand().execute();
            }
        }
    }
}
