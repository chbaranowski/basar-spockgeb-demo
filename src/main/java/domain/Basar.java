package domain;

import data.Position;
import data.User;

public interface Basar {
    
    User findByBasarNumber(String basarNumber);

    Position createPosition(String basarNumber, Long price, String description);
    
    Long getTotal();
    
    void buy(Iterable<Position> positions);

    User findUserWithId(Long id);

    Iterable<User> findAllUsers();

    void saveUser(User user);

    void deleteUserWithId(Long id);
    

}
