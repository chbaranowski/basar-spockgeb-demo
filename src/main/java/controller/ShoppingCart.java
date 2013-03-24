package controller;

import data.Position;

import java.io.Serializable;
import java.util.LinkedList;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LinkedList<Position> positions = new LinkedList<Position>();

    public ShoppingCart() {
    }
    
    public void addPosition(Position position) {
        positions.push(position);
    }

    public void removePosition(Long id) {
        Position position = null;
        for (Position actual : positions) {
            if(actual.getId().equals(id)){
                position = actual;
                break;
            }
        }
        positions.remove(position);
    }

    public Iterable<Position> getPositions() {
        return positions;
    }

    public void clear() {
        positions.clear();
    }

    public Long sum() {
        long sum = 0;
        for (Position position : positions) {
            sum += position.getPrice();
        }
        return sum;
    }

}
