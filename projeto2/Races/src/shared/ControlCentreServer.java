/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import GeneralRepository.RacesProxy;
import communication.Proxy.ServerInterface;
import communication.ServerChannel;
import communication.message.Message;
import communication.message.MessageException;
import communication.message.MessageType;
import java.net.SocketException;

/**
 *
 * @author Daniela Simões, 76771
 */
public class ControlCentreServer extends ControlCentre implements ServerInterface {
    
    private boolean serverEnded;
    private String name;
    
    public ControlCentreServer(RacesProxy races) {
        super(races);
        this.name = "Control Centre Server";
        this.serverEnded = false;
    }

    @Override
    public Message processAndReply(Message inMessage, ServerChannel scon) throws MessageException, SocketException {
        switch(inMessage.getType()){
            case TERMINATE:
                this.serverEnded = true;
                break;
            case reportResults:
                super.reportResults(inMessage.getInteger1());
                break;
            case proceedToPaddock:
                super.proceedToPaddock(inMessage.getInteger1());
                break;
            case waitForNextRace:
                super.waitForNextRace(inMessage.getInteger1());
                break;
            case goWatchTheRace:
                super.goWatchTheRace(inMessage.getInteger1());
                break;
            case haveIWon:
                boolean response = super.haveIWon(inMessage.getInteger1(), inMessage.getInteger2());
                return new Message(MessageType.ACK, response);
            case relaxABit:
                super.relaxABit();
                break;
        }
        
        return new Message(MessageType.ACK);
    }

    @Override
    public boolean serviceEnded() {
        return serverEnded;
    }
    
    @Override
    public String serviceName() {
        return this.name;
    }
}
