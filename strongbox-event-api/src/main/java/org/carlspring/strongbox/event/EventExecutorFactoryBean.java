package org.carlspring.strongbox.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.task.SyncTaskExecutor;

import javax.servlet.ServletContext;
import java.util.Optional;
import java.util.concurrent.Executor;

public class EventExecutorFactoryBean implements FactoryBean<Executor>
{

    private static final Logger logger = LoggerFactory.getLogger(EventExecutorFactoryBean.class);

    private final ServletContext servletContext;

    public EventExecutorFactoryBean(ServletContext servletContext)
    {
        super();
        this.servletContext = servletContext;
    }

    @Override
    public Executor getObject()
        throws Exception
    {
        Executor executor = Optional.ofNullable(servletContext)
                                    .flatMap(c -> Optional.ofNullable(lookupExecutor()))
                                    .orElse(new SyncTaskExecutor());
        
        logger.info(String.format("Using [%s] executor for Async events.", executor.getClass()));
        
        return executor;
    }

    private Executor lookupExecutor()
    {
        Executor result;
        if ((result = lookupJettyExecutor()) != null)
        {
            return result;
        }
        return null;
    }

    private Executor lookupJettyExecutor()
    {
        Executor executor = (Executor) servletContext.getAttribute("org.eclipse.jetty.server.Executor");
        if (executor == null)
        {
            return null;
        }

        logger.info("Jetty environment detected.");

        return executor;
    }

    @Override
    public Class<?> getObjectType()
    {
        return Executor.class;
    }

}
