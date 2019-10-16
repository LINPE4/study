package cn.enjoyedu.ch5.map;

import java.util.Objects;

/**
 * 类说明：
 */
public class KeyVo {
    private final int id;
    private final String Name;

    public KeyVo(int id, String name) {
        this.id = id;
        Name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyVo keyVo = (KeyVo) o;
        return id == keyVo.id &&
                Objects.equals(Name, keyVo.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name);
    }
}
