package com.jackson.workfront.services;

import com.jackson.workfront.domain.ConfigBean;
import com.jackson.workfront.repository.ConfigBeanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sarwar on 4/9/18.
 */
@Service
public class ConfigBeanService {

    private ConfigBeanRepository configBeanRepository;

    @Autowired
    public ConfigBeanService(ConfigBeanRepository configBeanRepository) {
        this.configBeanRepository = configBeanRepository;
    }

    @Transactional
    public ConfigBean saveConfigBean(ConfigBean configBean) throws DataAccessException{
        if(configBeanRepository.findByBeanName(configBean.getBeanName()) == null){
            return configBeanRepository.save(configBean);
        }
        else return null;
    }

    @Transactional(readOnly = true)
    public ConfigBean getConfigBeanByName(String beanName) throws DataAccessException{
        ConfigBean configBean = null;
        try{
            configBean = configBeanRepository.findByBeanName(beanName);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e){
            return null;
        }
        return configBean;
    }

    @Transactional(readOnly = true)
    public Iterable<ConfigBean> getAllConfigBeans() throws DataAccessException{
        return configBeanRepository.findAll();
    }

    @Transactional
    public void deleteConfigBean(ConfigBean configBean) throws DataAccessException {
        configBeanRepository.delete(configBean);
    }

}
