package com.gamersfamily.gamersfamily.model;

public interface PersistentObj {

    public Long getId();

    public void setId(Long id);

    public Integer getVersion();

    public void setVersion(Integer version);
}
