package JDI_hw1.entities;

import com.epam.jdi.tools.DataClass;
import lombok.Getter;

@Getter
public class UserEntity extends DataClass<UserEntity> {
    private String name;
    private String password;

    public UserEntity(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
