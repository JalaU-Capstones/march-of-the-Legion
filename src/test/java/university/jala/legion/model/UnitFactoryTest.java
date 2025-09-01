package university.jala.legion.model;

import org.junit.jupiter.api.Test;
import university.jala.legion.model.interfaces.ICharacter;
import university.jala.legion.model.units.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link UnitFactory} class.
 */
class UnitFactoryTest {

    @Test
    void testCreateCommander() {
        // It should return an instance of Commander.
        ICharacter unit = UnitFactory.createUnit("commander");
        assertInstanceOf(Commander.class, unit);
        assertEquals("Commander", unit.getType());
    }

    @Test
    void testCreateMedic() {
        // It should return an instance of Medic.
        ICharacter unit = UnitFactory.createUnit("medic");
        assertInstanceOf(Medic.class, unit);
        assertEquals("Medic", unit.getType());
    }

    @Test
    void testCreateTank() {
        // It should return an instance of Tank.
        ICharacter unit = UnitFactory.createUnit("tank");
        assertInstanceOf(Tank.class, unit);
        assertEquals("Tank", unit.getType());
    }

    @Test
    void testCreateSniper() {
        // It should return an instance of Sniper.
        ICharacter unit = UnitFactory.createUnit("sniper");
        assertInstanceOf(Sniper.class, unit);
        assertEquals("Sniper", unit.getType());
    }

    @Test
    void testCreateInfantry() {
        // It should return an instance of Infantry.
        ICharacter unit = UnitFactory.createUnit("infantry");
        assertInstanceOf(Infantry.class, unit);
        assertEquals("Infantry", unit.getType());
    }

    @Test
    void testCreateUnitIsCaseInsensitive() {
        // It should create the correct unit regardless of case.
        ICharacter unit = UnitFactory.createUnit("CoMmAnDeR");
        assertInstanceOf(Commander.class, unit);
    }

    @Test
    void testCreateUnknownUnitTypeThrowsException() {
        // It should throw an exception for an unknown unit type.
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            UnitFactory.createUnit("unknown");
        });
        assertEquals("Unknown unit type: unknown", exception.getMessage());
    }
}
