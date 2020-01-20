package fr.tjacob3.schoolbot.command;
import fr.tjacob3.schoolbot.command.Command.ExecutorType;

import java.lang.reflect.Method;

public final class SimpleCommand {
    private final String name, description, usage;
    private final ExecutorType executorType;
    private final Object object;
    private final Method method;

    public SimpleCommand(String name, String description, String usage, ExecutorType executorType, Object object, Method method) {
        super();
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.executorType = executorType;
        this.object = object;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return "`" + usage + "`";
    }

    public ExecutorType getExecutorType() {
        return executorType;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }
}
