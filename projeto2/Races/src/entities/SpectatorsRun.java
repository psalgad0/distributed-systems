package entities;

import shared.PaddockStub;
import shared.ControlCentreStub;
import shared.BettingCentreStub;
import GeneralRepository.RacesProxy;
import GeneralRepository.LogProxy;
import communication.Proxy.Proxy;
import communication.message.Message;
import communication.message.MessageType;
import java.util.ArrayList;
import settings.NodeSettsProxy;

/**
 * This class implements the main of the entity spectator.
 * @author Daniela Simões, 76771
 */
public class SpectatorsRun {
    private static PaddockStub paddock = new PaddockStub();
    private static ControlCentreStub controlCentre = new ControlCentreStub();
    private static BettingCentreStub bettingCentre = new BettingCentreStub();
    private static RacesProxy races = new RacesProxy();
    
    private static int N_OF_SPECTATORS;

    public static void main(String [] args) {
        LogProxy log = new LogProxy();
        
        /* init proxies */
        paddock = new PaddockStub();
        controlCentre = new ControlCentreStub();
        bettingCentre = new BettingCentreStub();
        /* end init proxies */
        
        NodeSettsProxy proxy = new NodeSettsProxy(); 
        N_OF_SPECTATORS = proxy.N_OF_SPECTATORS();
        
        ArrayList<Spectators> spectators = new ArrayList<>(N_OF_SPECTATORS);

        for(int i = 0; i < N_OF_SPECTATORS; i++){
            spectators.add(new Spectators((shared.IControlCentre) controlCentre, (shared.IBettingCentre) bettingCentre , (shared.IPaddock) paddock, (int) (Math.random() * (proxy.MAX_SPECTATOR_BET() - 200)) + 200, i, races, log));
        }
        
        for (Spectators spectator : spectators)
            spectator.start();
        
        for (Spectators spectator : spectators) { 
            try { 
                spectator.join ();
                System.err.printf("Spectator %d died!\n", spectator.getSpectatorId()); 
            } catch (InterruptedException e) {}
        }
                
        /* SEND TO LOG THAT SPECTATOR HAS FINISHED */
        Proxy.connect(proxy.SERVER_HOSTS().get("Log"), 
                proxy.SERVER_PORTS().get("Log"), 
                new Message(MessageType.TERMINATE));
        
    }
}
