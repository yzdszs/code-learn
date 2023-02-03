package com.pertree.designmodel.di;

import java.io.InputStream;
import java.util.List;

/**
 * 配置解析
 *
 * @author 王跃斌
 */
public interface BeanConfigParser {
    List<BeanDefinition> parse(InputStream inputStream);
    List<BeanDefinition> parse(String configContent);
}
