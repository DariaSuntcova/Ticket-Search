package ru.netology.repository;

import ru.netology.domain.Ticket;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;

public class TicketRepository {
    private Ticket[] repo = new Ticket[0];

    public Ticket findById(int id) {
        for (Ticket ticket : repo) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public void add(Ticket ticket) {
        if (findById(ticket.getId()) == null) {
            Ticket[] tmp = new Ticket[repo.length + 1];
            System.arraycopy(repo, 0, tmp, 0, repo.length);
            tmp[tmp.length - 1] = ticket;
            repo = tmp;
        } else {
            throw new AlreadyExistsException("Element with id: " + ticket.getId() + " already in the repository");
        }
    }

    public Ticket[] findAll() {
        return repo;
    }

    public void removeById(int id) {
        if (findById(id) != null) {
            Ticket[] tmp = new Ticket[repo.length - 1];
            int index = 0;
            for (Ticket ticket1 : repo) {
                if (ticket1.getId() != id) {
                    tmp[index] = ticket1;
                    index++;
                }
            }
            repo = tmp;
        } else {
            throw new NotFoundException("Element with id: " + id + " not found");
        }

    }

}
