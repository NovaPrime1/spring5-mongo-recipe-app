package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import org.springframework.stereotype.Service;

import java.util.Set;


public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
