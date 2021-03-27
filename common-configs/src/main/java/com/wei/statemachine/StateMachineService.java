package com.wei.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineException;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/7/1 0001.
 */
public class StateMachineService {
    private static final Logger log = LoggerFactory.getLogger(StateMachineService.class);
    @Autowired
    private StateMachineFactory<String,String> stateMachineFactory1;
    @Autowired
    private StateMachineFactory<String,String> stateMachineFactory2;
    @Autowired
    private StateMachineFactory<String,String> stateMachineFactory3;
    @Autowired
    private IStateMachineMappingConfig<EnumStateMachineType,String> stateMachineType;


    public String start(EnumStateMachineType stateMachineType){
       log.info("EnumStateMachineType:{}",stateMachineType);
        try {
            StateMachine<String, String> stateMachine = this.getStateMachine(stateMachineType);
            stateMachine.start();
            return stateMachine.getState().getId();
        }catch (StateMachineException e){
            log.info("StateMachineException :{}",e);
            throw new StateMachineException("StateMachineException :{}",e);
        }
    }

    public StateMachine<String,String> getStateMachine(EnumStateMachineType stateMachineType){
        log.info("EnumStateMachineType is {}",stateMachineType);
        try {
           switch (stateMachineType.ordinal()){
               case 0:
                   return this.stateMachineFactory1.getStateMachine();
               case 1:
                   return this.stateMachineFactory2.getStateMachine();
               case 2:
                   return this.stateMachineFactory3.getStateMachine();
               default:
                   throw new StateMachineException("EnumStateMachine exception");
           }

        }catch (Exception e){
            throw new StateMachineException("EnumStateMachine exception");
        }
    }

    public StateMachinePersister<String,String,String> getPersister(EnumStateMachineType stateMachineType){
        try {
            log.info("EnumStateMachineType is {}",stateMachineType);
            DefaultStateMachineMappingConfig config = new DefaultStateMachineMappingConfig();
           /* return this.stateMachineType.getPersisterMappings().get(stateMachineType);*/
           return DefaultStateMachineMappingConfig.persisters.get(stateMachineType);
        }catch (Exception e){
            throw new StateMachineException("EnumStateMachine.getPersister exception");
        }
    }
    public String sendEvent(EnumStateMachineType stateMachineType, String state, String event){
        log.info("StateMachine sendEvent, type={}, state = {}, event = {}",
                stateMachineType,state,event);
        try{
            StateMachine<String, String> stateMachine = this.getStateMachine(stateMachineType);
            this.getPersister(stateMachineType).restore(stateMachine,state);
            boolean isOk = stateMachine.sendEvent(event);
            if(!isOk){
                throw new StateMachineException(String.format("StateMachine sendEvent exception, type=%s, state = %s, event = %s",
                        stateMachineType,state,event));
            }
            return stateMachine.getState().getId();
        }catch (Exception e){
            throw new StateMachineException(String.format("StateMachine sendEvent exception, type=%s, state =%s, event = %s",
                    stateMachineType,state,event));
        }

    }

}
