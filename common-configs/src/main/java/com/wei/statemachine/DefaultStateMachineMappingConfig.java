package com.wei.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/7/1 0001.
 */
public class DefaultStateMachineMappingConfig implements IStateMachineMappingConfig<EnumStateMachineType,String>{
    private static final Logger log = LoggerFactory.getLogger(DefaultStateMachineMappingConfig.class);
    public static  Map<EnumStateMachineType, StateMachinePersister<String, String, String>> persisters;
    static{
        DefaultStateMachineMappingConfig config = new DefaultStateMachineMappingConfig();
        persisters = config.getPersisterMappings();
    }


    @Override
    public Map<EnumStateMachineType, String> getStateMachineMappings() {
        Map<EnumStateMachineType,String> stateMachineMappings =
                new HashMap<EnumStateMachineType,String>();
        stateMachineMappings.put(EnumStateMachineType.StateMachineType1,"stateMachineType1");
        stateMachineMappings.put(EnumStateMachineType.StateMachineType2,"stateMachineType2");
        stateMachineMappings.put(EnumStateMachineType.StateMachineType3,"stateMachineType3");
        return stateMachineMappings;
    }

    @Override
    public Map<EnumStateMachineType, StateMachinePersister<String, String, String>> getPersisterMappings() {
        Map<EnumStateMachineType, StateMachinePersister<String, String, String>> persisterMappings =
                new HashMap<>();
        InMemoryStateMachinePersist machinePersist1 = new InMemoryStateMachinePersist();
        StateMachinePersister<String, String, String> persist1 = new DefaultStateMachinePersister<>(machinePersist1);
        persisterMappings.put(EnumStateMachineType.StateMachineType1,persist1);
        log.debug("init persist1");
        InMemoryStateMachinePersist machinePersist2 = new InMemoryStateMachinePersist();
        StateMachinePersister<String, String, String> persist2 = new DefaultStateMachinePersister<>(machinePersist2);
        persisterMappings.put(EnumStateMachineType.StateMachineType2,persist2);
        log.debug("init persist2");
        InMemoryStateMachinePersist machinePersist3 = new InMemoryStateMachinePersist();
        StateMachinePersister<String, String, String> persist3 = new DefaultStateMachinePersister<>(machinePersist3);
        persisterMappings.put(EnumStateMachineType.StateMachineType3,persist3);
        log.debug("init persist3");
        return persisterMappings;
    }
}
