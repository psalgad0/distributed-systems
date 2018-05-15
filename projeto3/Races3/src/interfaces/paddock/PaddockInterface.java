/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.paddock;

import java.rmi.RemoteException;
import structures.vectorClock.VectorTimestamp;

/**
 *
 * @author Daniela
 */
public interface PaddockInterface {
    public VectorTimestamp proceedToPaddock(VectorTimestamp vt) throws RemoteException;
    public VectorTimestamp proceedToStartLine(VectorTimestamp vt) throws RemoteException;
    public VectorTimestamp goCheckHorses(VectorTimestamp vt) throws RemoteException;
    public VectorTimestamp summonHorsesToPaddock(VectorTimestamp vt) throws RemoteException;
}
