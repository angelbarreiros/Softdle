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
import java.time.LocalDateTime;

@Service
public class WinsServiceImpl implements WinsService {
    @Autowired
    private WinsDao winsDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Block<Wins> winsHistory(String username, int page, int size) {
        Slice<Wins> slice= winsDao.findAllByUserUsernameOrderByDateAsc(username, PageRequest.of(page,size));
        return new Block<>(slice.getContent(),slice.hasNext());
    }
    @Transactional
    @Override
    public void addWin(String username, int nofattempts, String language) {
        User user= userDao.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("not found"));
        user.setIsPlayed(true);
        Wins wins= Wins.builder().user(user).date(LocalDateTime.now()).numberOfAttempts(nofattempts).language(language).build();
        winsDao.save(wins);
    }
    @Transactional
    @Override
    public void addloose(String username, int nofattempts,String language) {
        User user= userDao.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("not found"));
        user.setIsPlayed(true);
        Wins wins= Wins.builder().user(user).date(LocalDateTime.now()).numberOfAttempts(nofattempts).language(language).build();
        winsDao.save(wins);
    }

    @Override
    public Boolean isPlayed(String username) {
        User user= userDao.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("not found"));
        return user.getIsPlayed();

    }



}
