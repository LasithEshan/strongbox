package org.carlspring.strongbox.storage.routing;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mtodorov
 */
@XmlRootElement(name = "rule-set")
@XmlAccessorType(XmlAccessType.FIELD)
public class MutableRuleSet
        implements Serializable
{

    @XmlAttribute(name = "storage-id")
    private String storageId;

    @XmlAttribute(name = "repository-id")
    private String repositoryId;

    @XmlElement(name = "rule")
    private List<MutableRoutingRule> routingRules = new ArrayList<>();

    public String getStorageId()
    {
        return storageId;
    }

    public void setStorageId(String storageId)
    {
        this.storageId = storageId;
    }

    public String getRepositoryId()
    {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId)
    {
        this.repositoryId = repositoryId;
    }

    public List<MutableRoutingRule> getRoutingRules()
    {
        return routingRules;
    }

    public void setRoutingRules(List<MutableRoutingRule> routingRules)
    {
        this.routingRules = routingRules;
    }
}
