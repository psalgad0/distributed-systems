/*
 * This file contains the messages constructors.
 */
package communication.message;
import GeneralRepository.Bet;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Daniela
 */
public class Message{
    
    private String message_s;
    private boolean message_b;
    private int message_i;
    private int message_i2;
    private int message_i3;
    private MessageType type;
    
        private HashMap<?, ?> map = null;

        
    public Message(MessageType type, int message){
        this.message_i = message;
        this.type = type; 
    }
    
    public Message(MessageType type, int message, int message2){
        this.message_i = message;
        this.message_i2 = message2;
        this.type = type; 
    }
    
    public Message(MessageType type, int message, boolean message2){
        this.message_i = message;
        this.message_b = message2;
        this.type = type; 
    }
    
    public Message(MessageType type, int message, int message2, int message3){
        this.message_i = message;
        this.message_i2 = message2;
        this.message_i3 = message3;
        this.type = type; 
    }
    
    public Message(MessageType type, String message){
        this.message_s = message;
        this.type = type; 
    }
    
    public Message(MessageType type, boolean message){
        this.message_b = message;
        this.type = type; 
    }
    
    public Message(MessageType type){
        this.type = type; 
    }
    
    /**
     * Construct to create Message with type and HashMap
     * @param type
     * @param map
     */
    public Message(MessageType type, HashMap<?, ?> map){
        this(type);
        this.map = map;
    }
    
    /**
     * Return Message Type
     * @return 
     */
    public MessageType getType(){
        return this.type;
    }
    
    /**
     * Return Integer HashMap
     * @return 
     */
    public HashMap<String, Integer> getStrIntMap(){
        return (HashMap<String, Integer>) this.map;
    }
    
    /**
     * Return String HashMap
     * @return 
     */
    public HashMap<String, String> getStrStrMap(){
        return (HashMap<String, String>) this.map;
    }
    
    /**
     * Return Integer
     * @return 
     */
    public int getInteger(){
        return this.message_i;
    }
    
    /**
     * Return Boolean
     * @return 
     */
    public boolean getBoolean(){
        return this.message_b;
    }

    public Bet getBet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LinkedList<Integer> getLinkedList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
