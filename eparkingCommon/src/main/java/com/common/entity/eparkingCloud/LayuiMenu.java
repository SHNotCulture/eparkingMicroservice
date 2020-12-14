package com.common.entity.eparkingCloud;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 12:292018-9-21
 * @Modified By:
 */
public class LayuiMenu {
    private  Integer ID;
    private  String Name;
    private  String iconCls;
    private  String xrc;
    private List<LayuiMenu> children;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getXrc() {
        return xrc;
    }

    public void setXrc(String xrc) {
        this.xrc = xrc;
    }

    public List<LayuiMenu> getChildren() {
        return children;
    }

    public void setChildren(List<LayuiMenu> children) {
        this.children = children;
    }
}
