package com.ckr.common.notification

import com.ckr.common.IStepExecutor
import com.ckr.common.ScriptContextRegistry
import groovy.json.JsonBuilder

import javax.net.ssl.HttpsURLConnection

class Telegram implements Serializable {

    private String _botToken

    Telegram(String botToken) {
        this._botToken = botToken
    }

    IStepExecutor getStepExecutor() {
        return ScriptContextRegistry.getContext().getStepExecutor()
    }

    def sendMessage(String chatId, String message) {
        stepExecutor.echo "send Message ${message} to chat with id ${chatId}"

        stepExecutor.echo("send to rest api https://api.telegram.org/bot${_botToken}/sendMessage")
        HttpsURLConnection connection = new URL("https://api.telegram.org/bot${_botToken}/sendMessage").openConnection() as HttpsURLConnection
        connection.setRequestMethod("POST")
        connection.setDoOutput(true)
        connection.setDoInput(true)
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")


        String payload = "{ \"chat_id\" : \"${chatId}\", \"text\" : \"${message}\", \"parse_mode\" : \"Markdown\" }"

        stepExecutor.echo("send Payload ${payload}")

        def writer = new OutputStreamWriter(connection.outputStream)
        writer.write(payload)
        writer.flush()
        writer.close()

        def reader = new InputStreamReader(connection.inputStream)
        String response = reader.readLines().join()
        reader.close()
        stepExecutor.echo "Response: ${response}"
    }

}
