package Modun3.service.role;

import Modun3.model.user.Role;
import Modun3.model.user.RoleName;

import java.util.List;

public interface iRoleService {
    List<Role> findAll();

    Role findByName(RoleName name);
}
