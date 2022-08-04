package com.sanket.designparkinglot.services;

import com.sanket.designparkinglot.exceptions.EntityAlreadyExistsException;
import com.sanket.designparkinglot.models.displayboard.DisplayBoard;
import com.sanket.designparkinglot.repositories.DisplayBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.Objects;
import java.util.Optional;

@Service
public class DisplayBoardService extends BaseService{

    private final DisplayBoardRepository displayBoardRepository;

    @Autowired
    public DisplayBoardService(DisplayBoardRepository displayBoardRepository) {
        this.displayBoardRepository = displayBoardRepository;
    }
    public DisplayBoard addDisplayBoard(String displayBoardNumber) throws EntityAlreadyExistsException {

        // check if display board already exist
        Optional<DisplayBoard> dbDisplayBoard = displayBoardRepository.findByDisplayBoardNumber(displayBoardNumber);
        if(dbDisplayBoard.isPresent()) {
            throw new EntityAlreadyExistsException(dbDisplayBoard.get().getClass().getSimpleName());
        }
        // create new display board
        DisplayBoard displayBoard = new DisplayBoard();
        displayBoard.setDisplayBoardNumber(displayBoardNumber);
        setCreateModelDefaults(displayBoard);
        return displayBoardRepository.save(displayBoard);
    }
}
