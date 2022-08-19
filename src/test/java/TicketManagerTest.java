import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;
import ru.netology.manager.TicketManager;
import ru.netology.repository.TicketRepository;

public class TicketManagerTest {

    TicketRepository repo = new TicketRepository();
    TicketManager manager = new TicketManager(repo);

    Ticket ticket1 = new Ticket(1, 2_895, "VKO", "LED", 85);
    Ticket ticket2 = new Ticket(2, 4_706, "DME", "IJK", 100);
    Ticket ticket3 = new Ticket(3, 14_006, "DME", "PKC", 85);
    Ticket ticket4 = new Ticket(4, 2_725, "ZIA", "AER", 230);
    Ticket ticket5 = new Ticket(5, 3_136, "SVO", "KZN", 95);
    Ticket ticket6 = new Ticket(6, 2_490, "SVO", "KZN", 100);

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
    }

    @Test
    public void shouldAddAlreadyId() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            manager.add(ticket1);
        });
    }

    @Test
    public void shouldFindById3() {
        Ticket expected = ticket3;
        Assertions.assertEquals(expected, manager.findById(3));
    }

    @Test
    public void shouldFindByIdNoTicket() {
        Ticket expected = null;

        Assertions.assertEquals(expected, manager.findById(7));
    }

    @Test
    public void shouldRemoveById5() {
        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4, ticket6};
        manager.removeById(5);
        Assertions.assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void shouldRemoveByIdNoTicket() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            manager.removeById(10);
        });
    }

    @Test
    public void shouldFindAllSameFromTo() {
        Ticket[] expected = {ticket6, ticket5};
        Assertions.assertArrayEquals(expected, manager.findAll("SVO", "KZN"));
    }

    @Test
    public void shouldFindOneSameFromTo() {
        Ticket[] expected = {ticket1};
        Assertions.assertArrayEquals(expected, manager.findAll("VKO", "LED"));
    }

    @Test
    public void shouldFindAllNoTickets() {
        Ticket[] expected = {};
        Assertions.assertArrayEquals(expected, manager.findAll("AER", "IJK"));
    }

}
