package backend.softdle.model.services;

import backend.softdle.model.entities.Wins;

public interface WinsService {
    Block<Wins> winsHistory(String username, int page, int size);
    void addWin(String username,int nofattempts);
    public void addloose(String username, int nofattempts);


}
