package it.unibo.multifirefly

import it.unibo.kactor.*
import unibo.basicomm23.interfaces.IApplMessage
import unibo.basicomm23.mqtt.MqttSupport
import unibo.basicomm23.utils.CommUtils
import kotlinx.coroutines.delay
import kotlin.random.Random

class Firefly(name: String, val R: Int, val C: Int) :
    ActorBasic(name, confined = true) {

    private val mqttSupport = MqttSupport()
    private val mqttBroker  = "tcp://localhost:1883"
    private val topic       = "lifegameIn"

    override suspend fun actorBody(msg: IApplMessage) {
        when (msg.msgId()) {
            "init" -> {
                CommUtils.outcyan("$name | starts at ($R,$C)")
                forward("accesa", "accesa(do)", name)
            }
            "accesa" -> {
                publishCellState()
                delay(Random.nextLong(1000, 5000))
                forward("spenta", "spenta(do)", name)
            }
            "spenta" -> {
                publishCellState()
                delay(Random.nextLong(1000, 5000))
                forward("accesa", "accesa(do)", name)
            }
        }
    }

    /*
     * Ciclo: accesa -> spenta -> accesa -> ...
     * Ogni stato emette cell(R,C) sul topic MQTT e aspetta un tempo random.
     */
    private suspend fun accesa() {
        CommUtils.outgreen("$name | ACCESA at ($R,$C)")
        publishCellState()
        val wait = Random.nextLong(1000, 5000)
        delay(wait)
        spenta()
    }

    private suspend fun spenta() {
        CommUtils.outred("$name | SPENTA at ($R,$C)")
        publishCellState()
        val wait = Random.nextLong(1000, 5000)
        delay(wait)
        accesa()  // ciclo infinito
    }

    /*
     * Pubblica un messaggio nel formato atteso da LifeGamePactorUsingMqtt:
     *   msg(firefly_R_C, dispatch, firefly_R_C, lifegame, cellstate, cell(R,C))
     *
     * LifeGamePactorUsingMqtt.elab() cerca payload.startsWith("cell")
     * e poi fa switchCellState(x, y) -> toggle dello stato della cella.
     */
    private fun publishCellState() {
        val payload = "cell($R,$C)"
        val applMsg = CommUtils.buildDispatch(name, "cellstate", payload, "lifegame")
        CommUtils.outcyan("$name | publish on $topic: $applMsg")
        mqttSupport.publish(topic, applMsg.toString(), 1, false)
    }
}