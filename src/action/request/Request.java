package action.request;

import action.command.Command;

/**
 * Created by Ethan on 16/4/30.
 */
public interface Request<T> extends Command {
    T getAnswer();
}
