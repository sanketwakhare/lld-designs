package com.sanket.designparkinglot.strategies.spotassignment;

import com.sanket.designparkinglot.models.EntryGate;
import com.sanket.designparkinglot.models.Spot;
import com.sanket.designparkinglot.models.Vehicle;

public interface SpotAssignmentStrategy {

    Spot assignSpot(Vehicle vehicle, EntryGate gate);
}
