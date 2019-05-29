package com.storefront.manage.model.pojo.rbac;

import com.shop.base.model.BasePojo;

public class RoleMenuKey extends BasePojo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu.role_id
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    private Long roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu.menu_id
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    private Long menuId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu.role_id
     *
     * @return the value of role_menu.role_id
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu.role_id
     *
     * @param roleId the value for role_menu.role_id
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu.menu_id
     *
     * @return the value of role_menu.menu_id
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu.menu_id
     *
     * @param menuId the value for role_menu.menu_id
     *
     * @mbggenerated Tue May 07 15:25:21 CST 2019
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}