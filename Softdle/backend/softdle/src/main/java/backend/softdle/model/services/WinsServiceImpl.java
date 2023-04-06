package backend.softdle.model.services;

import backend.softdle.model.entities.User;
import backend.softdle.model.entities.UserDao;
import backend.softdle.model.entities.Wins;
import backend.softdle.model.entities.WinsDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WinsServiceImpl implements WinsService {
    @Autowired
    private WinsDao winsDao;
    @Autowired
    private UserDao userDao;
    @Override
    public Block<Wins> winsHistory(String username, int page, int size) {
        Slice<Wins> slice= winsDao.findAllByUserUsernameOrderByDate(username, PageRequest.of(page,size));
        return new Block<>(slice.getContent(),slice.hasNext());
    }
    @Transactional
    @Override
    public void addWin(String username, int nofattempts) {
        User user= userDao.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("not found"));
        user.setStreak(user.getStreak()+1);
        Wins wins= Wins.builder().user(user).date(LocalDate.now()).numberOfAttempts(nofattempts).build();
        winsDao.save(wins);
    }
    public void addloose(String username, int nofattempts) {
        User user= userDao.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("not found"));
        user.setStreak(0);
        Wins wins= Wins.builder().user(user).date(LocalDate.now()).numberOfAttempts(nofattempts).build();
        winsDao.save(wins);
    }




}
