package com.wei.statemachine;

import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.Map;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/7/1 0001.
 */
public interface IStateMachineMappingConfig<E,F> {

    Map<E,F>  getStateMachineMappings();

    Map<E,StateMachinePersister<String,String,String>> getPersisterMappings();
}
