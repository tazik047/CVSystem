package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Favorite;

public interface FavoritesDAO {

	Boolean insertFavorites(long companiesId, long studentsId);

	Collection<Favorite> getFavorites(long companiesId);
}
