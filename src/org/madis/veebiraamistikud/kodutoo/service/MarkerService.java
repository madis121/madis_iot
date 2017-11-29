package org.madis.veebiraamistikud.kodutoo.service;

import java.util.List;

import javax.persistence.Query;

import org.madis.iot.veebijuhtimisega.nutikodu.service.BaseService;
import org.madis.veebiraamistikud.kodutoo.models.Marker;

public class MarkerService extends BaseService {

	public Marker getMarker(Marker marker) {
		Query query = getEntityManager().createQuery("FROM Marker WHERE latitude=:lat AND longitude=:lng");
		query.setParameter("lat", marker.getLatitude());
		query.setParameter("lng", marker.getLongitude());
		return (Marker) query.getSingleResult();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Marker> getMarkers() {
		Query query = getEntityManager().createQuery("from Marker");
		List markers = query.getResultList();
		return markers;
	}
	
	public void insertMarker(Marker marker) {
		if (marker != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(marker);
			getEntityManager().getTransaction().commit();
			System.out.println("Inserted: " + marker);
		}
	}
	
	public void deleteMarker(Marker marker) {
		if (marker != null) {
			Marker dbMarker = getMarker(marker);
			System.out.println("Removing: " + dbMarker);
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(dbMarker);
			getEntityManager().getTransaction().commit();
		}
	}
	
}
