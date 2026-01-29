package model.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Department implements Serializable {
    private Integer Id;
    private String name;

    public Department(Integer id, String name) {
        Id = id;
        this.name = name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(Id, that.Id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, name);
    }

    @Override
    public String toString() {
        return "Department{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                '}';
    }

}
