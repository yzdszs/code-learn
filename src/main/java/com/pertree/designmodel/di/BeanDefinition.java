package com.pertree.designmodel.di;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean 定义配置的对象映射
 *
 *
     配置文件beans.xml
     <beans>
     <bean id="rateLimiter" class="com.xzg.RateLimiter">
     <constructor-arg ref="redisCounter"/>
     </bean>

     <bean id="redisCounter" class="com.xzg.redisCounter" scope="singleton" lazy-init="true">
     <constructor-arg type="String" value="127.0.0.1">
     <constructor-arg type="int" value=1234>
     </bean>
     </bean
 *
 * @author 王跃斌
 * @date 2023/1/26
 */
public class BeanDefinition {
    /**
     * bean id
     */
    private String id;

    /**
     * bean 的全类名
     */
    private String className;

    /**
     * bean 的构造参数
     */
    private List<ConstructorArg> constructorArgs = new ArrayList<>();

    /**
     * 对象的类型
     */
    private Scope scope = Scope.SINGLETON;

    /**
     * 是否懒加载
     */
    private boolean lazyInit = false;

    public enum Scope {
        /**
         * 单例模式
         */
        SINGLETON,
        /**
         * 原型模式
         */
        PROTOTYPE;
    }

    public static class ConstructorArg {

        /**
         * 是否是引用对象
         */
        private boolean isRef;

        /**
         * 参数的类型
         */
        private Class<?> type;

        /**
         * 参数的值
         */
        private Object arg;

        public ConstructorArg() {
        }

        public ConstructorArg(Class<?> type, Object arg) {
            this.type = type;
            this.arg = arg;
        }

        public boolean isRef() {
            return isRef;
        }

        public void setRef(boolean ref) {
            isRef = ref;
        }

        public Class getType() {
            return type;
        }

        public void setType(Class type) {
            this.type = type;
        }

        public Object getArg() {
            return arg;
        }

        public void setArg(Object arg) {
            this.arg = arg;
        }
    }

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isSingleton() {
        return scope.equals(Scope.SINGLETON);
    }

    public void addConstructorArg(ConstructorArg constructorArg) {
        this.constructorArgs.add(constructorArg);
    }
}
