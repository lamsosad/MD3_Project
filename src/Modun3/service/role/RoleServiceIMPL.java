package Modun3.service.role;

import Modun3.model.user.Role;
import Modun3.model.user.RoleName;


import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements iRoleService {
    public static List<Role> roles = new ArrayList<>();
    static {
        roles.add(new Role(1,RoleName.USER));
        roles.add(new Role(2,RoleName.PM));
        roles.add(new Role(3,RoleName.ADMIN));

    }
    @Override
    public List<Role> findAll() {
        return roles;
    }

    @Override
    public Role findByName(RoleName name) {
        for (int i = 0; i < roles.size(); i++) {
            if (roles.get(i).getName()==name){
                return roles.get(i);
            }
        }
        return null;
    }
}
