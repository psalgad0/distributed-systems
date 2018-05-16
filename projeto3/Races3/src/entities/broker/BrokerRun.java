package entities.broker;

import structures.constants.RegistryConfigs;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniela
 */
public class BrokerRun {
    public static void main(String [] args) {   
        
        // nome do sistema onde está localizado o serviço de registos RMI
        String rmiRegHostName;
        // port de escuta do serviço
        int rmiRegPortNumb;

        RegistryConfigs rc = new RegistryConfigs("config.ini");
        rmiRegHostName = rc.registryHost();
        rmiRegPortNumb = rc.registryPort();
        
        interfaces.stable.IBroker si = null;
        interfaces.controlCentre.IBroker cci = null;
        interfaces.bettingCentre.IBroker bci = null;
        interfaces.racingTrack.IBroker rti = null;
        interfaces.paddock.IBroker pi = null;
        interfaces.log.IBroker li = null;
        
        try
        { 
            Registry registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
            li = (interfaces.log.IBroker) registry.lookup (RegistryConfigs.logNameEntry);
        }
        catch (RemoteException e)
        { 
            System.out.println("Exception thrown while locating log: " + e.getMessage () + "!");
            System.exit (1);
        }
        catch (NotBoundException e)
        { 
            System.out.println("Log is not registered: " + e.getMessage () + "!");
            System.exit(1);
        }
        
        try
        { 
            Registry registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
            si = (interfaces.stable.IBroker) registry.lookup (RegistryConfigs.stableNameEntry);
        }
        catch (RemoteException e)
        { 
            System.out.println("Exception thrown while locating stable: " + e.getMessage () + "!");
            System.exit (1);
        }
        catch (NotBoundException e)
        { 
            System.out.println("Stable is not registered: " + e.getMessage () + "!");
            System.exit(1);
        }
        
        try
        { 
            Registry registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
            cci = (interfaces.controlCentre.IBroker) registry.lookup (RegistryConfigs.controlCentreNameEntry);
        }
        catch (RemoteException e)
        { 
            System.out.println("Exception thrown while locating control centre: " + e.getMessage () + "!");
            System.exit (1);
        }
        catch (NotBoundException e)
        { 
            System.out.println("Control centre is not registered: " + e.getMessage () + "!");
            System.exit(1);
        }
        
        try
        { 
            Registry registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
            bci = (interfaces.bettingCentre.IBroker) registry.lookup (RegistryConfigs.bettingCentreNameEntry);
        }
        catch (RemoteException e)
        { 
            System.out.println("Exception thrown while locating betting centre: " + e.getMessage () + "!");
            System.exit (1);
        }
        catch (NotBoundException e)
        { 
            System.out.println("Betting centre is not registered: " + e.getMessage () + "!");
            System.exit(1);
        }
        
        try
        { 
            Registry registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
            rti = (interfaces.racingTrack.IBroker) registry.lookup (RegistryConfigs.racingTrackNameEntry);
        }
        catch (RemoteException e)
        { 
            System.out.println("Exception thrown while locating racing track: " + e.getMessage () + "!");
            System.exit (1);
        }
        catch (NotBoundException e)
        { 
            System.out.println("Racing track is not registered: " + e.getMessage () + "!");
            System.exit(1);
        }
        
        try
        { 
            Registry registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
            pi = (interfaces.paddock.IBroker) registry.lookup (RegistryConfigs.paddockNameEntry);
        }
        catch (RemoteException e)
        { 
            System.out.println("Exception thrown while locating paddock: " + e.getMessage () + "!");
            System.exit (1);
        }
        catch (NotBoundException e)
        { 
            System.out.println("Paddock is not registered: " + e.getMessage () + "!");
            System.exit(1);
        }
   
         
        Broker broker = new Broker(si, cci, bci, rti, pi, li);
        
        System.out.println("Number of broker: 1 ");
        
        broker.start();
        
        try { 
            broker.join ();
        } catch (InterruptedException e) {}
        
        
        System.out.println("Say to log that I have finished!");
        
        try {
            li.finished();
        } catch (RemoteException ex) {
            Logger.getLogger(BrokerRun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Done!");
    }
}
