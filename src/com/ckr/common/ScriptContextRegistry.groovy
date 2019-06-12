package com.ckr.common

class ScriptContextRegistry implements Serializable {

    private static IScriptContext _context

    static void registerScriptContext(IScriptContext context) {
        this._context = context
    }

    static void registerDefaultScriptContext(Object steps) {
        registerScriptContext(new DefaultScriptContext(steps))
    }

    static IScriptContext getContext() {
        return _context
    }

}
