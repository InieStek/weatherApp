package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@RequiredArgsConstructor
public class LocalizationRepository implements LocalizationRepositoryInt {

    private final SessionFactory sessionFactory;

    @Override
    public Localization save(Localization localization) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(localization);
        transaction.commit();
        session.close();
        return localization;
    }

    @Override
    public List<Localization> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Localization> localizations = session.createQuery("from Localization").getResultList();
        transaction.commit();
        session.close();
        return localizations;
    }
}
