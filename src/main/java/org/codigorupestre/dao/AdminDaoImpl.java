package org.codigorupestre.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.codigorupestre.bean.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("adminDao")
public class AdminDaoImpl implements AdminDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public boolean save(Admin admin) {

		// MapSqlParameterSource paramMap = new MapSqlParameterSource();
		// paramMap.addValue("nombre", admin.getNombre());
		// paramMap.addValue("cargo", admin.getCargo());
		// paramMap.addValue("fechaCreacion", admin.getFechaCreacion());

		BeanPropertySqlParameterSource paramMap = new BeanPropertySqlParameterSource(admin);

		return jdbcTemplate.update(
				"INSERT INTO Admin (nombre, cargo, fechaCreacion) values (:nombre, :cargo, :fechaCreacion)",
				paramMap) == 1;
	}

	public List<Admin> findAll() {
		String query = "SELECT * FROM Admin";
		return jdbcTemplate.query(query, new RowMapper<Admin>() {
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin admin = new Admin();

				admin.setIdAd(rs.getInt("idAd"));
				admin.setCargo(rs.getString("cargo"));
				admin.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
				admin.setNombre(rs.getString("nombre"));
				return admin;
			}
		});
	}

	public Admin findById(int id) {
		String sql = "select * from Admin where idAd = :idAd";
		// return (Admin) jdbcTemplate.query(sql,
		// new MapSqlParameterSource("idAd", id), new AdminRowMapper());
		return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("idAd", id), new AdminRowMapper());
	}

	public List<Admin> findByNombre(String nombre) {
		return jdbcTemplate.query("SELECT * FROM Admin where nombre like :nombre",
				new MapSqlParameterSource("nombre", "%" + nombre + "%"), new AdminRowMapper());
	}

	public boolean update(Admin admin) {
		String sql = "UPDATE Admin set nombre=:nombre, cargo=:cargo WHERE idAd = :idAd ";
		return jdbcTemplate.update(sql, new BeanPropertySqlParameterSource(admin)) == 1;
	}

	public boolean delete(int idAd) {
		String sql = "DELETE FROM Admin where idAd=:idAd";
		return jdbcTemplate.update(sql, new MapSqlParameterSource("idAd", idAd)) == 1;
	}

	@Transactional
	public int[] saveAll(List<Admin> admins) {
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(admins.toArray());
		return jdbcTemplate.batchUpdate("INSERT INTO Admin (idAd, nombre, cargo, fechaCreacion) values (:idAd,:nombre, :cargo, :fechaCreacion)", batchArgs);
	}

}
