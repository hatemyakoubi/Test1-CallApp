package hatem.yakoubi.testcallerapp.DAO;

import java.util.List;

import hatem.yakoubi.testcallerapp.Profile;

public interface ProfileDAO {
    List<Profile> getProfiles();
    Profile getProfileById(long id);
    long addProfile(Profile profile);
    int updateProfile(Profile profile,long id);
    int deleteProfile(long id);
}
