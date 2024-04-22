package dao;

import java.time.LocalDate;
import java.util.List;

import pojos.Role;
import pojos.User;

public interface IUserDao {

	User getUserDetails(int userId);

}
