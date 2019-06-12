package com.ckr.common

class StepExecutor implements IStepExecutor {

    private _steps

    StepExecutor(def steps) {
        _steps = steps
    }

    @Override
    void echo(String message) {
        this._steps.echo "${message}"
    }
}
