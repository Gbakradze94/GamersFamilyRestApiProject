package com.gamersfamily.gamersfamily.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity implements PersistentObj, Serializable {

    @Id
    @Column(name="id",unique = true,nullable = false)
    private Long id = IdGenerator.createId();

    @Version
    private Integer version;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersistentObj)) {

            return false;
        }

        PersistentObj other
                = (PersistentObj) o;

        // if the id is missing, return false
        if (id == null) return false;

        // equivalence by id
        return id.equals(other.getId());
    }

    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return super.hashCode();
        }
    }

}
