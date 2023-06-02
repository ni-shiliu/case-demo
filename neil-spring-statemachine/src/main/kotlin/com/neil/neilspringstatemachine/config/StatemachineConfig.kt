package com.neil.neilspringstatemachine.config

import com.neil.neilspringstatemachine.enums.State
import com.neil.neilspringstatemachine.events.Event
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachine
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.listener.StateMachineListener
import org.springframework.statemachine.listener.StateMachineListenerAdapter
import java.util.EnumSet

/**
 *
 * @author nihao
 * @date 2023/6/2
 */
@Configuration
@EnableStateMachine
class StatemachineConfig : EnumStateMachineConfigurerAdapter<State, Event>() {

    override fun configure(config: StateMachineConfigurationConfigurer<State, Event>) {
        config
            .withConfiguration()
            .machineId("order-machine")
            .autoStartup(true)
            .listener(listener())
    }

    override fun configure(states: StateMachineStateConfigurer<State, Event>) {
        states
            .withStates()
            .initial(State.S1)
            .states(EnumSet.allOf(State::class.java))
            .end(State.S3)
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<State, Event>) {
        transitions
            .withExternal()
            .source(State.S1).target(State.S2).event(Event.E1)
            .and()
            .withExternal()
            .source(State.S2).target(State.S3).event(Event.E2)
    }

    @Bean
    fun listener(): StateMachineListener<State, Event>? {
        return object : StateMachineListenerAdapter<State, Event>() {
            override fun stateChanged(
                from: org.springframework.statemachine.state.State<State, Event>?,
                to: org.springframework.statemachine.state.State<State, Event>?
            ) {
                println("state changed to " + to?.id)
            }
        }
    }
}