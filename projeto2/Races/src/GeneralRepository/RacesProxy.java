/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneralRepository;

import communication.Proxy.ClientProxy;
import communication.message.Message;
import communication.message.MessageType;
import communication.message.MessageWrapper;
import java.util.LinkedList;
import settings.NodeSettsProxy;

/**
 *
 * @author Daniela
 */
public class RacesProxy{
    
    private final String SERVER_HOST;
    private final int SERVER_PORT;

    public RacesProxy(){
        NodeSettsProxy proxy = new NodeSettsProxy(); 
        SERVER_HOST = proxy.SERVER_HOSTS().get("Races");
        SERVER_PORT = proxy.SERVER_PORTS().get("Races");
    }
    
    private MessageWrapper communicate(Message m){
        return ClientProxy.connect(SERVER_HOST,  SERVER_PORT, m);
    }
   
    /**
     *
     * @param raceNumber
     * @return
     */
    public synchronized Bet chooseBet(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBet();
    }

    /**
    *
    * Method to verify if a given horse was selected to a race.
     * @param horseJockeyID
     * @param horseStepSize
     * @param raceNumber
     * @return 
    */
    public boolean horseHasBeenSelectedToRace(int horseJockeyID, int horseStepSize, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, horseJockeyID, horseStepSize, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param id
     * @param stepSize
     */
    public void setHorseJockeyStepSize(int id, int stepSize){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, id, stepSize));
    }
    
    /**
     *
     * @param id
     * @return
     */
    public int getHorseJockeyStepSize(int id){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, id));
        return result.getMessage().getInteger();
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public synchronized boolean areThereAnyWinners(int raceNumber){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    };
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public synchronized boolean haveIWon(int raceNumber){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    };
    
    /**
    *
    * Method to get the winner of the race.
     * @param raceNumber
     * @return 
    */
    public LinkedList<Integer> getWinner(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getLinkedList();
    }
    
    /**
    *
    * Method to verify if has more races to happen.
     * @return 
    */
    public boolean hasMoreRaces(){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt));
        return result.getMessage().getBoolean();
    }
    
    /**
    *
    * Method to allow the HorseJockey to make a move in the race.
    * @param horseId The ID of the HorseJockey.
     * @param raceNumber
    */
    public void makeAMove(int horseId, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, horseId));
    }
    
    /**
     *
     * @param horseId
     * @param raceNumber
     * @return
     */
    public int getHorseIteration(int horseId, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, horseId));
        return result.getMessage().getInteger();
    }
    
    /**
     *
     * @param horseId
     * @param raceNumber
     * @return
     */
    public int getStandingPosition(int horseId, int raceNumber){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, horseId));
        return result.getMessage().getInteger();
    }
    
    /**
     *
     * @param horseJockeyId
     * @param raceNumber
     * @return
     */
    public boolean nextMovingHorse(int horseJockeyId, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, horseJockeyId));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param horseId
     * @param raceNumber
     * @return
     */
    public boolean horseFinished(int horseId, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, horseId));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public boolean horsesFinished(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public int getNRunningHorses(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getInteger();
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public synchronized int getCurrentRaceDistance(int raceNumber){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getInteger();
    }
    
    /* condition states */
    /* Racing Track */

    /**
     *
     * @param raceNumber
     * @return
     */

    public synchronized boolean getStartTheRace(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param startTheRace
     * @param raceNumber
     */
    public synchronized void setStartTheRace(boolean startTheRace, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
    }
    /* Stable */

    /**
     *
     * @param raceNumber
     * @return
     */

    public synchronized int getWakedHorsesToPaddock(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getInteger();
    }
    
    /**
     *
     * @param raceNumber
     */
    public synchronized void addWakedHorsesToPaddock(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public synchronized boolean getAnnouncedNextRace(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param announcedNextRace
     * @param raceNumber
     */
    public synchronized void setAnnouncedNextRace(boolean announcedNextRace, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, announcedNextRace));
    }
    /* Paddock */

    /**
     *
     * @param raceNumber
     * @return
     */

    public boolean allSpectatorsArrivedAtPaddock(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param raceNumber
     */
    public void addNSpectatorsArrivedAtPaddock(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public boolean allHorseJockeyLeftThePadock(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param raceNumber
     */
    public void addNHorseJockeyLeftThePadock(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
    }
    
    /* Control Centre */

    /**
     *
     * @param set
     * @param raceNumber
     */

    public synchronized void setReportResults(boolean set, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, set));
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public synchronized boolean getReportResults(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param set
     * @param raceNumber
     */
    public synchronized void setProceedToPaddock(boolean set, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, set));
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public synchronized boolean getProceedToPaddock(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public synchronized boolean allNHorsesInPaddock(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param raceNumber
     */
    public synchronized void addNHorsesInPaddock(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
    }
    
    /* Betting Centre */

    /**
     *
     * @param raceNumber
     * @return
     */

    public Integer waitAddedBet(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getInteger();
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public boolean allSpectatorsBettsAceppted(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param bet
     * @param raceNumber
     */
    public void addBetOfSpectator(Bet bet, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, bet));
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public boolean allSpectatorsBetted(int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param raceNumber
     */
    public void waitAcceptedTheBet(int raceNumber){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
    }
   
    /**
     *
     * @param i
     * @param raceNumber
     */
    public void acceptBet(int i, int raceNumber){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public Integer poolWaitingToBePaidSpectators(int raceNumber){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getInteger();
    }
    
    /**
     *
     * @param i
     * @param raceNumber
     */
    public void addWaitingToBePaidSpectator(int i, int raceNumber){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, i));
    }
    
    /**
     *
     * @param raceNumber
     * @return
     */
    public boolean allSpectatorsPaid(int raceNumber){
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param i
     * @param raceNumber
     * @return
     */
    public synchronized boolean getPaidSpectators(int i, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, i));
        return result.getMessage().getBoolean();
    }
    
    /**
     *
     * @param i
     * @param set
     * @param raceNumber
     */
    public synchronized void setPaidSpectators(int i, boolean set, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, set));
    }
    
    /**
     *
     * @param spectatorId
     * @param raceNumber
     * @return
     */
    public Bet getSpectatorBet(int spectatorId, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, spectatorId));
        return result.getMessage().getBet();
    }
    
    /**
     *
     * @param horseId
     * @param raceNumber
     * @return
     */
    public double getHorseOdd(int horseId, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber, horseId));
        return result.getMessage().getDouble();
    }
    
    /**
     *
     * @param horseId
     * @param raceNumber
     * @return
     */
    public int getHorsePosition(int horseId, int raceNumber){        
        MessageType mt = MessageType.valueOf(new Object(){}.getClass().getEnclosingMethod().getName());
        MessageWrapper result = communicate(new Message(mt, raceNumber));
        return result.getMessage().getInteger();
    }
    /* end condition states */
    
}