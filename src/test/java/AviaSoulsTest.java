import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {

    @Test
    public void shouldCompareTicketsByPrice() {
        Ticket ticket1 = new Ticket("Москва", "Сочи", 5_000, 10, 12);
        Ticket ticket2 = new Ticket("Москва", "Сочи", 7_000, 11, 13);

        int actual = ticket1.compareTo(ticket2);

        assertTrue(actual < 0);
    }

    @Test
    public void shouldSearchAndSortByPrice() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Москва", "Сочи", 7_000, 10, 12);
        Ticket ticket2 = new Ticket("Москва", "Сочи", 5_000, 11, 13);
        Ticket ticket3 = new Ticket("Москва", "Сочи", 6_000, 12, 14);
        Ticket ticket4 = new Ticket("Москва", "Казань", 3_000, 10, 12);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] expected = {ticket2, ticket3, ticket1};
        Ticket[] actual = manager.search("Москва", "Сочи");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayWhenNoTicketsFound() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket = new Ticket("Москва", "Сочи", 5_000, 10, 12);
        manager.add(ticket);

        Ticket[] expected = {};
        Ticket[] actual = manager.search("Москва", "Казань");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsByFlightTime() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket ticket1 = new Ticket("Москва", "Сочи", 5_000, 10, 12);
        Ticket ticket2 = new Ticket("Москва", "Сочи", 6_000, 10, 14);

        int actual = comparator.compare(ticket1, ticket2);

        assertTrue(actual < 0);
    }

    @Test
    public void shouldSearchAndSortByFlightTime() {
        AviaSouls manager = new AviaSouls();
        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket ticket1 = new Ticket("Москва", "Сочи", 5_000, 10, 14);
        Ticket ticket2 = new Ticket("Москва", "Сочи", 7_000, 10, 12);
        Ticket ticket3 = new Ticket("Москва", "Сочи", 6_000, 10, 13);
        Ticket ticket4 = new Ticket("Москва", "Казань", 4_000, 10, 11);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);

        Ticket[] expected = {ticket2, ticket3, ticket1};
        Ticket[] actual = manager.searchAndSortBy(
                "Москва",
                "Сочи",
                comparator
        );

        assertArrayEquals(expected, actual);
    }
}