package com.example.newyorktimestopstories;

import io.reactivex.Scheduler;

/**
 * Created by raheelkhan on 2/19/17.
 */
public interface SchedulerProvider {
    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
