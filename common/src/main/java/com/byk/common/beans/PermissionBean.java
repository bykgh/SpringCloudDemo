package com.byk.common.beans;

/**
 * 资源bean
 * @author yikai.bi
 */
public class PermissionBean {

    private Long id;
    /**
     * 资源名称
     */
    private String name;

    /**
     *资源类型：MENU,BUTTON
     */
    private String type;

    /**
     * 请求方式
     * get、post 等
     */
    private String method;

    //service_prefix
    /**
     * 服务前缀
     */
    private String servicePrefix;

    /**
     * 网关服务地址
     * zuul_prefix
     */
    private String zuulPrefix;

    /**
     *访问url地址
     */
    private String url;

    /**
     *权限代码字符串
     */
    private String percode;

    /**
     *父结点id
     */
    private Integer parentid;

    /**
     *父结点id列表串
     */
    private String parentids;

    /**
     *排序号
     */
    private String sortstring;

    /**
     *是否可用,1：可用，0不可用
     */
    private String available;

    /**
     * 是否展示  TRUE 展示  FALSE 不展示
     */
    private Boolean show;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getServicePrefix() {
        return servicePrefix;
    }

    public void setServicePrefix(String servicePrefix) {
        this.servicePrefix = servicePrefix;
    }

    public String getZuulPrefix() {
        return zuulPrefix;
    }

    public void setZuulPrefix(String zuulPrefix) {
        this.zuulPrefix = zuulPrefix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPercode() {
        return percode;
    }

    public void setPercode(String percode) {
        this.percode = percode;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getParentids() {
        return parentids;
    }

    public void setParentids(String parentids) {
        this.parentids = parentids;
    }

    public String getSortstring() {
        return sortstring;
    }

    public void setSortstring(String sortstring) {
        this.sortstring = sortstring;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PermissionBean){
            PermissionBean permissionBean = (PermissionBean)obj;
            return  this.name.equals(permissionBean.name)
                    && this.available.equals(permissionBean.available)
                    && this.method.equals(permissionBean.method)
                    && this.parentid.equals(permissionBean.parentid)
                    && this.parentids.equals(permissionBean.parentids)
                    && this.percode.equals(permissionBean.percode)
                    && this.servicePrefix.equals(permissionBean.servicePrefix)
                    && this.sortstring.equals(permissionBean.sortstring)
                    && this.zuulPrefix.equals(permissionBean.zuulPrefix)
                    && this.type.equals(permissionBean.type)
                    && this.url.equals(permissionBean.url)
                    && this.show.equals(permissionBean.show);
        }
        return super.equals(obj);
    }

    /**
     * 第一步:定义一个初始值，一般来说取17
     * int result = 17;
     * 第二步：分别解析自定义类中与equals方法相关的字段（假如hashCode中考虑的字段在equals方法中没有考虑，则两个equals的对象就很可能具有不同的hashCode）
     *     情况一：字段a类型为boolean 则[hashCode] = a ? 1 : 0;
     *     情况二：字段b类型为byte/short/int/char, 则[hashCode] = (int)b;
     *     情况三：字段c类型为long， 则[hashCode] = (int) (c ^ c>>>32);
     *     情况四：字段d类型为float, 则[hashCode] = d.hashCode()
     *            (内部调用的是Float.hashCode(d)， 而该静态方法内部调用的另一个静态方法是Float.floatToIntBits(d))
     *     情况五：字段e类型为double, 则[hashCode] = e.hashCode()
     *            (内部调用的是Double.hashCode(e)， 而该静态方法内部调用的另一个静态方法是Double.doubleToLongBits(e),
     *             得到一个long类型的值之后，跟情况三进行类似的操作，得到一个int类型的值)
     *     情况六：引用类型，若为null则hashCode为0,否则递归调用该引用类型的hashCode方法。
     *     情况七：数组类型。
     *            (要获取数组类型的hashCode,可采用如下方法：
     *            s[0]*31 ^ (n-1) + s[1] * 31 ^ (n-2) + ..... + s[n-1]，
     *            该方法正是String类的hashCode实现所采用的算法）
     * 第三步：对于涉及到的各个字段，采用第二步中的方式，将其依次应用于下式：
     * result = result * 31 + [hashCode];
     * 补充说明：
     * 如果初始值result不取17而取0的话，则对于hashCode为0的字段来说就没有区分度了，这样更容易产生冲突。
     * 比如:
     * 两个自定义类中，一个类比另一个类多出来一个或者几个字段，其余字段全部一样，
     * 分别new出来2个对象，这2个对象共有的字段的值全是一样的，
     * 而对于多来的那些字段的值正好都是0,并且在计算hashCode时这些多出来的字段又是最先计算的，
     * 这样的话，则这两个对象的hashCode就会产生冲突.
     * @return
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 31 * result + (available == null ? 0 : available.hashCode());
        result = 31 * result + (method == null ? 0 : method.hashCode());
        result = 31 * result + (parentid == null ? 0 : parentid.hashCode());
        result = 31 * result + (parentids == null ? 0 : parentids.hashCode());
        result = 31 * result + (percode == null ? 0 : percode.hashCode());
        result = 31 * result + (servicePrefix == null ? 0 : servicePrefix.hashCode());
        result = 31 * result + (sortstring == null ? 0 : sortstring.hashCode());
        result = 31 * result + (zuulPrefix == null ? 0 : zuulPrefix.hashCode());
        result = 31 * result + (type == null ? 0 : type.hashCode());
        result = 31 * result + (url == null ? 0 : url.hashCode());
        result = 31 * result + (show == null ? 0 : show.hashCode());
        return result;
    }
}
