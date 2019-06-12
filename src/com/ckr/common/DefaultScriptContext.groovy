package com.ckr.common

class DefaultScriptContext implements  IScriptContext, Serializable {

    private _steps

    DefaultScriptContext(steps) {
        _steps = steps
    }

    @Override
    IStepExecutor getStepExecutor() {
        return new StepExecutor(this._steps)
    }
}
