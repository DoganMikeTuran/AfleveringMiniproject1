package server

import BankContent
import Request
import Response
import WebContent
import java.net.ServerSocket
import kotlin.concurrent.thread
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking




class WebServer(val bankContent: WebContent, val port: Int = 80) {
    var running = true
   
    //val gson =
    //-> Gson()
    //-> GsonBuilder().setPrettyPrinting().create() // for pretty print feature

    fun handleSocket (req : Request, res : Response) {

        if(req.command.split("/")[1] == "getAccountFromId") {
            val id = req.command.split("/")[2];
            if(req.command.isNotEmpty()){
                val acc = bankContent.getAccountById(id.toInt());

                res.setBody("<h1> Owner of account ID " + acc.id + " is: " + acc.name + "</h1>")
                res.send()
            }
        }
    }

    fun start() {
        val serverSocket = ServerSocket(port)
        println("Server running...")
        while (running)
        {
            val socket = serverSocket.accept()
            thread {
                handleSocket(Request(socket.getInputStream()), Response(socket.getOutputStream()))
            }
        }
    }

    fun stop() {
        running = false;
        println("Server stopped...")
    }
}
fun main() {
    val content = BankContent(/* filename, ... */)
    val server = WebServer(content,550)
    server.start()
    server.stop()
}
