package vehiclerental.model;

import com.vehiclerental.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Vehicle vehicle;

    @BeforeEach
    public void setup() {
        vehicle = new Vehicle("V1", 200);
    }

    @Test
    void testIsAvailable() {
        vehicle.book(7, 8);

        assertTrue(vehicle.isAvailable(1,4));
        assertFalse(vehicle.isAvailable(7,12));
    }

    @Test
    void testBook() {
        int expected = 0;
        int actual = vehicle.book(7, 7);

        assertEquals(expected, actual);

        expected = 600;
        actual = vehicle.book(2, 5);

        assertEquals(expected, actual);
    }
}
