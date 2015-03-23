package basar.domain;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import basar.data.Position;
import basar.data.PositionRepository;
import basar.data.User;
import basar.data.UserRepository;

@Service
public class BasarService implements Basar {

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public User findByBasarNumber(String basarNumber) {
        return userRepository.findByBasarNumber(basarNumber);
    }

    @Override
    @Transactional
    public Position createPosition(String basarNumber, Long price, String description) {
        User seller = userRepository.findByBasarNumber(basarNumber);
        Position position = new Position();
        position.setDescription(description);
        position.setPrice(price);
        position.setSeller(seller);
        position.setPurchased(false);
        return positionRepository.save(position);
    }

    @Override
    @Transactional
    public Long getTotal() {
        return positionRepository.getTotal();
    }

    @Override
    @Transactional
    public void buy(Iterable<Position> positions) {
        for (Position position : positions) {
            position.setPurchased(true);
            position.setTimeOfPurchase(new Date());
        }
        positionRepository.save(positions);
    }

    @Override
    @Transactional
    public User findUserWithId(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public Iterable<User> findAllUsers() {
       return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserWithId(Long id) {
        userRepository.delete(id);
    }

}
