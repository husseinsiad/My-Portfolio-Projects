
/*getAllLocationBySuperHero*/
select * from location l join sighting s on l.id=s.locationid join super_sighting ss on s.id=ss.sightingid join superhero sh on sh.id=so.superheroid   where sh.id=?;

/*getAllOrganizationBySuperhero*/
select * from organization o join super_org so on o.id=so.organizationid join superhero s on s.id=so.superheroid where s.id=?;

/*getAllSightingByLocation*/
select * from sighting s join location l on l.id=s.locationid where l.id=?;

/*getAllSightingByDate*/
select * from sighting where date=?;

/*getAllSuperHeroByLocation*/
select * from superhero sh join super_sighting ss on sh.id=ss.superheroid join sighting s on s.id=ss.sightingid join location l on l.id=s.locationid where l.id=?;

/*getAllSuperHeroByOrganization*/
select * from superhero s join super_org so on s.id=so.superheroid join organization o on o.id=so.organizationid where o.id=?;
 


