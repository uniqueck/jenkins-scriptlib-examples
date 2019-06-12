import com.ckr.common.ScriptContextRegistry
import com.ckr.common.notification.Telegram

def call(Map parameters = [:]) {
    ScriptContextRegistry.registerDefaultScriptContext(this)

    withCredentials([usernamePassword(credentialsId: 'TELEGRAM_BOT', passwordVariable: 'TOKEN', usernameVariable: '')]) {
        new Telegram("${TOKEN}").sendMessage(parameters?.chatId ?: "", parameters?.message ?: "")
    }



}
