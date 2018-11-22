package com.nixsolutions.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import com.nixsolutions.usermanagement.User;

public class HsqldbUserDao implements UserDao {
	
		private static final String SELECT_ALL_QUERY = "SELECT id, fistname, lastname, dateofbirth FROM users";
		private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
		private static final String FIND_BY_ID_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id = ?";
	    private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
	    private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
		private ConnectionFactory connectionFactory;
		
		public HsqldbUserDao() {
			}
		
		public HsqldbUserDao(ConnectionFactory connectionFactory) {
			this.connectionFactory = connectionFactory;
		}

		
			public ConnectionFactory getConnectionFactory() {
			return connectionFactory;
		}



		public void setConnectionFactory(ConnectionFactory connectionFactory) {
			this.connectionFactory = connectionFactory;
		}



	@Override
	public User create(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstname());
			statement.setString(2, user.getLastname());
			statement.setDate(3, new Date(user.getDateOfBithd().getTime()));
			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of the inserted rows: " + n);
			}
			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				user.setId(new Long(keys.getLong(1)));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (DatabaseException e) {
			throw e;
		}catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void update(User user) throws DatabaseException {
		Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(UPDATE_QUERY);
            statement.setString(1,user.getFirstname());
            statement.setString(2,user.getLastname());
            statement.setDate(3,new Date(user.getDateOfBithd().getTime()));
            statement.setLong(4, user.getId());
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of updated raws: " + number);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

	}

	@Override
	public void delete(User user) throws DatabaseException {
		Connection connection = connectionFactory.createConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement(DELETE_QUERY);
            statement.setLong(1,user.getId());
            int number = statement.executeUpdate();
            if(number != 1){
                throw new DatabaseException("Number of deleted raws: " + number);
            }


            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

	}

	@Override
	public User find(Long id) throws DatabaseException {
		User user;
        try {
            user = null;
            Connection connection = connectionFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            preparedStatement.setLong(1,id);
            ResultSet oneUserResultSet = preparedStatement.executeQuery();
           if (oneUserResultSet.next()){
                user = new User();
                user.setId(new Long(oneUserResultSet.getLong("ID")));
                user.setFirstname(oneUserResultSet.getString("FIRSTNAME"));
                user.setLastname(oneUserResultSet.getString("LASTNAME"));
                user.setDateOfBithd(oneUserResultSet.getDate("DATEOFBIRTH"));
            }
            connection.close();
            preparedStatement.close();
            oneUserResultSet.close();
            return user;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

	@Override
	public Collection findAll() throws DatabaseException {
		Collection result = new LinkedList();
		
		try {
			Connection connection = connectionFactory.createConnection();
			@SuppressWarnings("unused")
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstname(resultSet.getString(2));
				user.setLastname(resultSet.getString(3));
				user.setDateOfBithd(resultSet.getDate(4));
				result.add(user);
			}
		}catch (DatabaseException e){
			throw e;
		}catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
		return result;
	}

}
