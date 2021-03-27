package com.wei.statemachine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.HashMap;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/7/1 0001.
 */
public class InMemoryStateMachinePersist implements StateMachinePersist<String,String,String>{
   private static final Logger log = LoggerFactory.getLogger(InMemoryStateMachinePersist.class);

    @Override
    public void write(StateMachineContext<String, String> stateMachineContext, String state) throws Exception {

    }

    @Override
    public StateMachineContext<String, String> read(String state) throws Exception {
        log.debug("state is " + state);
        StateMachineContext<String,String> context = new DefaultStateMachineContext<String, String>(state,null,new HashMap<>(),null);
        return context;
    }
}
