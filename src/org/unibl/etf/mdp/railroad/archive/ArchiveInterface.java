package org.unibl.etf.mdp.railroad.archive;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import org.unibl.etf.mdp.railroad.model.Meta;
import org.unibl.etf.mdp.railroad.model.Report;

public interface ArchiveInterface extends Remote {
	
	public boolean upload(byte[] data, String name, String user) throws RemoteException;
	public ArrayList<Meta> list() throws RemoteException;
	public Report download(String id) throws RemoteException;
}
