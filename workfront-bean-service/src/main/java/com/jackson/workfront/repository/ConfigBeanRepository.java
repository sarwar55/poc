package com.jackson.workfront.repository;

import com.jackson.workfront.domain.ConfigBean;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by sarwar on 4/9/18.
 */

public interface ConfigBeanRepository extends CrudRepository<ConfigBean, Integer> {
    ConfigBean findByBeanName(String beanName);
}
