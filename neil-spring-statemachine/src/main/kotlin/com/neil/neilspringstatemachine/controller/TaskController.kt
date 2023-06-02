package com.neil.neilspringstatemachine.controller

import com.neil.neilspringstatemachine.enums.State
import com.neil.neilspringstatemachine.events.Event
import org.springframework.statemachine.StateMachine
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @author nihao
 * @date 2023/6/2
 */
@RestController
@RequestMapping("/api/v1/statemachine")
class TaskController(
    private val stateMachine: StateMachine<State, Event>
) {

    @PostMapping("sendEvent")
    fun changeState() {
        stateMachine.sendEvent(Event.E1)
        stateMachine.sendEvent(Event.E2)
    }
}