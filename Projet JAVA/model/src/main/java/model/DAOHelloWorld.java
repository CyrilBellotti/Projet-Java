package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Class DAOHelloWorld.
 *
 * @author Jean-Aymeric Diet
 */
class DAOHelloWorld extends DAOEntity<HelloWorld> {

	/**
	 * Instantiates a new DAO hello world.
	 *
	 * @param connection
	 *          the connection
	 * @throws SQLException
	 *           the SQL exception
	 */
	public DAOHelloWorld(final Connection connection) throws SQLException {
		super(connection);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#create(model.Entity)
	 */
	@Override
	public boolean create(final HelloWorld entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#delete(model.Entity)
	 */
	@Override
	public boolean delete(final HelloWorld entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#update(model.Entity)
	 */
	@Override
	public boolean update(final HelloWorld entity) {
		// Not implemented
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#find(int)
	 */
	@Override
	public HelloWorld find(final int id) {
		HelloWorld helloWorld = new HelloWorld();

		try {
			final String sql = "{call helloworldById(?)}";
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setInt(1, id);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if (resultSet.first()) {
				helloWorld = new HelloWorld(id, resultSet.getString("key"), resultSet.getString("message"));
			}
			return helloWorld;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see model.DAOEntity#find(java.lang.String)
	 */
	@Override
	public HelloWorld find(final String key) {
		HelloWorld helloWorld = new HelloWorld();

		try {
			final String sql = "{call helloworldByKey(?)}";
			final CallableStatement call = this.getConnection().prepareCall(sql);
			call.setString(1, key);
			call.execute();
			final ResultSet resultSet = call.getResultSet();
			if (resultSet.first()) {
				helloWorld = new HelloWorld(resultSet.getInt("id"), key, resultSet.getString("message"));
			}
			return helloWorld;
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void buildMap()
	{
		map aMap = new map();
		try
		{
			final String sql0 = "{call truncate()}";
			final CallableStatement call0 = getConnection().prepareCall(sql0);
			call0.execute();
			for (int y = 0; y<12; y++)
			{
				for (int x = 0; x<20; x++)
				{
					final String sql = "{call build_map(?, ?, ?, ?)}";
					final CallableStatement call = getConnection().prepareCall(sql);
					call.setInt(1, aMap.getNiveau());
					call.setInt(2, y);
					call.setInt(3, x);
					call.setString(4,Character.toString(aMap.getMap(y, x)));
					call.execute();
				}
			}
		}
		catch (final Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public int[][] translateMap()
	{
		int [][] map = new int [12][20];
		try
		{
			for (int y = 0; y<12; y++)
			{
				for (int x = 0; x<20; x++)
				{
					final String sql = "{call get_map(?, ?)}";
					final CallableStatement call = getConnection().prepareCall(sql);
					call.setInt(1, y);
					call.setInt(2, x);
					call.execute();
					ResultSet result = call.getResultSet();
					if (result.first()) 
					{
						map[y][x] = result.getInt(1);
					}
				}
			}
		}
		catch (final Exception e) 
		{
			e.printStackTrace();
		}
		return map;
	}
}
